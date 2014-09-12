package cn.focus.qademo.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.focus.qademo.dao.QuestionDAO;
import cn.focus.qademo.model.Question;
import cn.focus.qademo.util.ObjectToXMLUtil;
import cn.focus.qademo.util.TF_IDFAlgorithm;
import cn.focus.qademo.util.WordSeg;

@Service
public class MatchService {
    @Autowired
    private TF_IDFAlgorithm tf_idf;
    @Autowired
    private QuestionDAO questionDAO;

    private static HashMap<Integer, HashMap<String, Float>> tf_idfs_Map = null;
    
    // 输入的问题与数据库中已存问题计算出的tiidf，与问题id对应
    private static HashMap<Integer, Float> id_rs_tfidf = new HashMap<Integer, Float>();

    private void initFromDataBase(Integer build_id, Integer type) {
        tf_idfs_Map = tf_idf.BUILD_ID_TFIDF_result(build_id, type);
        try {
            File file = new File("temp/" + build_id.toString() + "_" + type.toString() + ".xml");
            ObjectToXMLUtil.objectXmlEncoder(tf_idfs_Map, file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initFromFile(File file) {
        try {
            tf_idfs_Map = ObjectToXMLUtil.objectXmlDecoder(file);
            if (tf_idfs_Map == null) {
                System.out.println("Match 初始化失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void init(Integer build_id, Integer type) {
        File file = new File("temp/" + build_id.toString() + "_" + type.toString() + ".xml");
        if (file.exists()) {
            initFromFile(file);
        } else {
            initFromDataBase(build_id, type);
        }
    }

    public ArrayList<Question> match(String str) {
        id_rs_tfidf.clear();
        ArrayList<String> cutwords = WordSeg.parse(str);
        ArrayList<Question> questions = new ArrayList<Question>();
        Iterator iter = tf_idfs_Map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            HashMap<String, Float> tf_idf_Map = (HashMap<String, Float>) entry.getValue();
            Integer key = Integer.valueOf(entry.getKey().toString());
            for (int i = 0; i < cutwords.size(); i++) {
                // 看现在处理的词是否在tf_idf_Map的可以key中，若在进行处理
                if (tf_idf_Map.get(cutwords.get(i)) != null) {
                    if (id_rs_tfidf.get(key) != null) {
                        Float lastValue = id_rs_tfidf.get(key);
                        Float value = lastValue + tf_idf_Map.get(cutwords.get(i));
                        id_rs_tfidf.put(key, value);
                    } else {
                        id_rs_tfidf.put(key, tf_idf_Map.get(cutwords.get(i)));
                    }
                }
            }
            // 新输入问题与此entry内hashMap内相同的词的tf_idf和
            if (id_rs_tfidf.get(key) != null) {
                float tf_idf_sum2 = id_rs_tfidf.get(key);
                // 此entry对应hashMap内tf_idf和
                float tf_idf_sum1 = 0.0f;
                Iterator iter1 = tf_idf_Map.keySet().iterator();
                while (iter1.hasNext()) {
                    tf_idf_sum1 += tf_idf_Map.get(iter1.next());
                }
                id_rs_tfidf.put(key, tf_idf_sum2 / tf_idf_sum1);
            }
        }
        ArrayList list = mapSortByValue(id_rs_tfidf);
        int i = 5;

        if (list.size() < 5) {
            i = list.size();
        }
        for(int j = 0;j<i;j++)
        {
            Entry<Integer, Float> entry = (Entry<Integer, Float>) list.get(j);            
            questions.add(questionDAO.get(entry.getKey()));
        }
        return questions;
    }

    public static Integer findMax(HashMap<Integer, Float> id_rs_tfidf) {
        Integer max = -1;
        if (id_rs_tfidf.keySet().size() > 0) {
            Iterator iter = id_rs_tfidf.keySet().iterator();
            max = (Integer) iter.next();
            Float base = id_rs_tfidf.get(max);
            while (iter.hasNext()) {
                Float compare = id_rs_tfidf.get(iter.next());
                if (base < compare) {
                    base = compare;
                    max = (Integer) iter.next();
                }
            }
        }
        return max;
    }

    public static ArrayList<Entry<Integer, Float>> mapSortByValue(HashMap<Integer, Float> map) {
        ArrayList<Entry<Integer, Float>> list = new ArrayList<Entry<Integer, Float>>((Collection) map.entrySet());

        Collections.sort(list, new Comparator<Object>() {

            public int compare(Object e1, Object e2) {
                Float v1 = ((Entry<Integer, Float>) e1).getValue();
                Float v2 = ((Entry<Integer, Float>) e2).getValue();
                if (v1 >= v2) {
                    return -1;
                } else {
                    return 1;
                }

            }
        });

        for (Entry<Integer, Float> e : list) {
            System.out.println(e.getKey() + "  " + e.getValue());
        }
        return list;
    }

}
