package cn.focus.qademo.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.FilterModifWord;

public class WordSeg {
    static {
        initStopWordsAndStopNatures();
    }

    public static ArrayList<String> parse(String str) {
        List<Term> parse = ToAnalysis.parse(str);
        List<Term> terms = FilterModifWord.modifResult(parse);
        ArrayList<String> words = new ArrayList<String>();
        for (Term t : terms) {
            if (t.getNatureStr() != null) {
                words.add(t.getName());
            }
        }
        return words;
    }

    public static void initStopWordsAndStopNatures() {

        List<String> stopWord = new ArrayList<String>();

        System.out.println("init stopwords and natures");
        // 停词表
        String stopWordFile = "temp/stopword.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(stopWordFile), "UTF-8"));
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                stopWord.add(line);
            }
        } catch (Exception e) {
            System.out.println("创建停词表失败");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FilterModifWord.insertStopWords(stopWord);
        stopWord.clear();
        System.out.println("init stopwords and natures success!!");
    }
}
