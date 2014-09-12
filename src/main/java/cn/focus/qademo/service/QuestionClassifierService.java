package cn.focus.qademo.service;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;


import cn.focus.qademo.dao.QuestionDAO;
import cn.focus.qademo.dao.QuestionTypeDAO;
import cn.focus.qademo.dao.WhiteWordDAO;
import cn.focus.qademo.model.Question;
import cn.focus.qademo.model.QuestionWhiteWord;

/**
 * @author 薛庆元
 *
 */
@Service
public class QuestionClassifierService {

    /**
     * @param args
     */
   
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private WhiteWordDAO whiteWordDAO;
    @Autowired
    private QuestionTypeDAO questionTypeDAO;
    
    //输入问题，输出问题所属类型。
    public  double classifyByBayes(String question) throws Exception{
        double label=-1;
        List<Question> questionID=questionDAO.getQuestionIDLabeled();
        
        //定义数据格式
        Attribute att1 = new Attribute("法律政策");
        Attribute att2 = new Attribute("位置交通");
        Attribute att3 = new Attribute("风水");
        Attribute att4 = new Attribute("房价");
        Attribute att5 = new Attribute("楼层");
        Attribute att6 = new Attribute("户型");
        Attribute att7 = new Attribute("小区配套");
        Attribute att8 = new Attribute("贷款");
        Attribute att9 = new Attribute("买房时机");
        Attribute att10 = new Attribute("开发商");
        FastVector labels = new FastVector();
        labels.addElement("1");
        labels.addElement("2");
        labels.addElement("3");
        labels.addElement("4");
        labels.addElement("5");
        labels.addElement("6");
        labels.addElement("7");
        labels.addElement("8");
        labels.addElement("9");
        labels.addElement("10");
        Attribute att11 = new Attribute("类别", labels);
        
        FastVector attributes = new FastVector();
        attributes.addElement(att1);
        attributes.addElement(att2);
        attributes.addElement(att3);
        attributes.addElement(att4);
        attributes.addElement(att5);
        attributes.addElement(att6);
        attributes.addElement(att7);
        attributes.addElement(att8);
        attributes.addElement(att9);
        attributes.addElement(att10);
        attributes.addElement(att11);
        Instances dataset = new Instances("Test-dataset", attributes, 0);
        dataset.setClassIndex(10);
        
        Classifier classifier=null;
        if(!new File("naivebayes.model").exists()){
            //添加数据
            double []values=new double[11];
            for(int i=0;i<questionID.size();i++){
                for(int m=0;m<11;m++){values[m]=0;}
                int whitewordcount=0;
                whitewordcount=questionDAO.getHitWhiteWordNum(questionID.get(i).getId());
                if(whitewordcount!=0){
                    List<QuestionWhiteWord> questionwhiteword=questionDAO.getHitQuestionWhiteWord(questionID.get(i).getId());
                    for(int j=0;j<questionwhiteword.size();j++){
                        values[getAttIndex(questionwhiteword.get(j).getWordId())-1]++;
                    }
                    for(int m=0;m<11;m++){values[m]=values[m]/whitewordcount;}
                }
                values[10]=questionID.get(i).getType()-1;
                Instance inst = new Instance(1.0, values);
                dataset.add(inst);     
            }
            //构造分类器
            classifier = new NaiveBayes();
            classifier.buildClassifier(dataset);
            SerializationHelper.write("naivebayes.model", classifier);  
        }
        else{
            classifier = (Classifier) SerializationHelper.read("naivebayes.model");
        }

        System.out.println("*************begin evaluation*******************");
        Evaluation evaluation = new Evaluation(dataset);
        evaluation.evaluateModel(classifier, dataset);//按道理说，这里应该使用另一份数据，而不是训练集data。
        System.out.println(evaluation.toSummaryString());      
        
        
        //分类
        System.out.println("*************begin classification*******************");
        Instance subject = new Instance(1.0, getQuestionVector(question));
        subject.setDataset(dataset);
        label = classifier.classifyInstance(subject);
        System.out.println("label: "+label);
        
//        double dis[]=classifier.distributionForInstance(inst);
//        for(double i:dis){
//            System.out.print(i+"    ");
//        }        
        
        System.out.println(questionID.size());
        return label+1;
    }
    
    //输入问题，输出问题所属类型。
    public  double classifyByTree(String question) throws Exception{
        double label=-1;
        List<Question> questionID=questionDAO.getQuestionIDLabeled();
        
        //定义数据格式
        Attribute att1 = new Attribute("法律政策");
        Attribute att2 = new Attribute("位置交通");
        Attribute att3 = new Attribute("风水");
        Attribute att4 = new Attribute("房价");
        Attribute att5 = new Attribute("楼层");
        Attribute att6 = new Attribute("户型");
        Attribute att7 = new Attribute("小区配套");
        Attribute att8 = new Attribute("贷款");
        Attribute att9 = new Attribute("买房时机");
        Attribute att10 = new Attribute("开发商");
        FastVector labels = new FastVector();
        labels.addElement("1");
        labels.addElement("2");
        labels.addElement("3");
        labels.addElement("4");
        labels.addElement("5");
        labels.addElement("6");
        labels.addElement("7");
        labels.addElement("8");
        labels.addElement("9");
        labels.addElement("10");
        Attribute att11 = new Attribute("类别", labels);
        
        FastVector attributes = new FastVector();
        attributes.addElement(att1);
        attributes.addElement(att2);
        attributes.addElement(att3);
        attributes.addElement(att4);
        attributes.addElement(att5);
        attributes.addElement(att6);
        attributes.addElement(att7);
        attributes.addElement(att8);
        attributes.addElement(att9);
        attributes.addElement(att10);
        attributes.addElement(att11);
        Instances dataset = new Instances("Test-dataset", attributes, 0);
        dataset.setClassIndex(10);
        
        Classifier classifier=null;
        if(!new File("J48.model").exists()){
            //添加数据
            double []values=new double[11];
            for(int i=0;i<questionID.size();i++){
                for(int m=0;m<11;m++){values[m]=0;}
                int whitewordcount=0;
                whitewordcount=questionDAO.getHitWhiteWordNum(questionID.get(i).getId());
                if(whitewordcount!=0){
                    List<QuestionWhiteWord> questionwhiteword=questionDAO.getHitQuestionWhiteWord(questionID.get(i).getId());
                    for(int j=0;j<questionwhiteword.size();j++){
                        values[getAttIndex(questionwhiteword.get(j).getWordId())-1]++;
                    }
                    for(int m=0;m<11;m++){values[m]=values[m]/whitewordcount;}
                }
                values[10]=questionID.get(i).getType()-1;
                Instance inst = new Instance(1.0, values);
                dataset.add(inst);     
            }
            //构造分类器
            classifier = new J48();
            classifier.buildClassifier(dataset);
            SerializationHelper.write("J48.model", classifier);  
        }
        else{
            classifier = (Classifier) SerializationHelper.read("J48.model");
        }

        System.out.println("*************begin evaluation*******************");
        Evaluation evaluation = new Evaluation(dataset);
        evaluation.evaluateModel(classifier, dataset);//按道理说，这里应该使用另一份数据，而不是训练集data。
        System.out.println(evaluation.toSummaryString());      
        
        
        //分类
        System.out.println("*************begin classification*******************");
        Instance subject = new Instance(1.0, getQuestionVector(question));
        subject.setDataset(dataset);
        label = classifier.classifyInstance(subject);
        System.out.println("label: "+label);
        
//        double dis[]=classifier.distributionForInstance(inst);
//        for(double i:dis){
//            System.out.print(i+"    ");
//        }        
        
        System.out.println(questionID.size());
        return label+1;
    }
    //输入待分类问题，输出一个问题向量。
    public  double[] getQuestionVector(String question) {
        // TODO Auto-generated method stub
        List<Term> parse = IndexAnalysis.parse(question);
        System.out.println(parse);
        String name=null;
        double values[]=new double[11];
        int whiteWordNum=0;
        for(int m=0;m<11;m++){
            values[m]=0;
        }
        
        for(int i=0;i<parse.size();i++){
            
            System.out.println("正在处理第"+i+"个词。");
            name=parse.get(i).getName();
            for(int j=0;j<10;j++){
                int times=belongQuestionClass(name,j+1);//times的值只可能是0和1
                values[j]=values[j]+times;
                whiteWordNum=whiteWordNum+times;
            }
            
        }
        if(whiteWordNum!=0){
            for(int m=0;m<11;m++){
                values[m]=values[m]/whiteWordNum;
            }
        }

        return values;
    }
    //输入问题，输出问题所属类型。  
    public double classifyByFre(String question){
        double []temp=getQuestionVector(question);
        int flag=0;
        for(int i=0;i<11;i++){
        if(temp[i]>temp[flag]) flag=i;
        }
        return flag+1;
    }
    
    //输入白词id，输出其所属问题的类型。
    public int getAttIndex(int wordid){
        int index=-1;
        String word=whiteWordDAO.getParentWordName(wordid);
        index=questionTypeDAO.getID(word);
        return index;
    }
    //输入一个任意词，输出该词是否属于特定问题的白词。1属于，0不属于。
    public int belongQuestionClass(String word,int questionid){
        int flag=0;
        int parentid=-1;
        parentid=getWhiteWordClass(questionid);
        flag=whiteWordDAO.belongWhiteClass(word,parentid);
        return flag;
        
    }
    //输入问题编号，输出对应白词类编号。（请确保输入的问题编号存在）
    public int getWhiteWordClass(int questionid){    
        return whiteWordDAO.getWordClass(questionTypeDAO.get(questionid).getType());
    }
    //输入问题，输出问题所属类型。
    public  double classifyByKnn(String question) throws Exception{
        double label=-1;
        List<Question> questionID=questionDAO.getQuestionIDLabeled();
        
        //定义数据格式
        Attribute att1 = new Attribute("法律政策");
        Attribute att2 = new Attribute("位置交通");
        Attribute att3 = new Attribute("风水");
        Attribute att4 = new Attribute("房价");
        Attribute att5 = new Attribute("楼层");
        Attribute att6 = new Attribute("户型");
        Attribute att7 = new Attribute("小区配套");
        Attribute att8 = new Attribute("贷款");
        Attribute att9 = new Attribute("买房时机");
        Attribute att10 = new Attribute("开发商");
        FastVector labels = new FastVector();
        labels.addElement("1");
        labels.addElement("2");
        labels.addElement("3");
        labels.addElement("4");
        labels.addElement("5");
        labels.addElement("6");
        labels.addElement("7");
        labels.addElement("8");
        labels.addElement("9");
        labels.addElement("10");
        Attribute att11 = new Attribute("类别", labels);
        
        FastVector attributes = new FastVector();
        attributes.addElement(att1);
        attributes.addElement(att2);
        attributes.addElement(att3);
        attributes.addElement(att4);
        attributes.addElement(att5);
        attributes.addElement(att6);
        attributes.addElement(att7);
        attributes.addElement(att8);
        attributes.addElement(att9);
        attributes.addElement(att10);
        attributes.addElement(att11);
        Instances dataset = new Instances("Test-dataset", attributes, 0);
        dataset.setClassIndex(10);
        
        Classifier classifier=null;
        if(!new File("knn.model").exists()){
            //添加数据
           
            for(int i=0;i<questionID.size();i++){
                
                double []values=new double[11];
                for(int m=0;m<11;m++){values[m]=0;}
                int whitewordcount=0;
                whitewordcount=questionDAO.getHitWhiteWordNum(questionID.get(i).getId());
                if(whitewordcount!=0){
                    List<QuestionWhiteWord> questionwhiteword=questionDAO.getHitQuestionWhiteWord(questionID.get(i).getId());
                    for(int j=0;j<questionwhiteword.size();j++){
                        values[getAttIndex(questionwhiteword.get(j).getWordId())-1]++;
                    }
                    for(int m=0;m<11;m++){values[m]=values[m]/whitewordcount; System.out.println(m+"<>"+values[m]);}
                }
                System.out.println("第"+i+"个问题。");
                System.out.println(questionID.get(i).getQuestion());
                values[10]=questionID.get(i).getType()-1;
                Instance inst = new Instance(1.0, values);
                dataset.add(inst);     
            }
            //构造分类器
            IBk ibk = new IBk();
            ibk.setKNN(3);
            classifier=ibk;
            classifier.buildClassifier(dataset);
            SerializationHelper.write("knn.model", classifier);  
        }
        else{
            classifier = (Classifier) SerializationHelper.read("knn.model");
            System.out.println("串行化解析。");
        }

        System.out.println("*************begin evaluation*******************");
        Evaluation evaluation = new Evaluation(dataset);
        evaluation.evaluateModel(classifier, dataset);//按道理说，这里应该使用另一份数据，而不是训练集data。
        System.out.println(evaluation.toSummaryString());      
        
        
        //分类
        System.out.println("*************begin classification*******************");
        Instance subject = new Instance(1.0, getQuestionVector(question));
        subject.setDataset(dataset);
        label = classifier.classifyInstance(subject);
        System.out.println("label: "+label);
        
//        double dis[]=classifier.distributionForInstance(inst);
//        for(double i:dis){
//            System.out.print(i+"    ");
//        }        
        
        System.out.println(questionID.size());
        return label+1;
    }
    public Instances getInstances(){
        List<Question> questionID=questionDAO.getQuestionIDLabeled();
        
        //定义数据格式
        Attribute att1 = new Attribute("法律政策");
        Attribute att2 = new Attribute("位置交通");
        Attribute att3 = new Attribute("风水");
        Attribute att4 = new Attribute("房价");
        Attribute att5 = new Attribute("楼层");
        Attribute att6 = new Attribute("户型");
        Attribute att7 = new Attribute("小区配套");
        Attribute att8 = new Attribute("贷款");
        Attribute att9 = new Attribute("买房时机");
        Attribute att10 = new Attribute("开发商");
        FastVector labels = new FastVector();
        labels.addElement("1");
        labels.addElement("2");
        labels.addElement("3");
        labels.addElement("4");
        labels.addElement("5");
        labels.addElement("6");
        labels.addElement("7");
        labels.addElement("8");
        labels.addElement("9");
        labels.addElement("10");
        Attribute att11 = new Attribute("类别", labels);
        
        FastVector attributes = new FastVector();
        attributes.addElement(att1);
        attributes.addElement(att2);
        attributes.addElement(att3);
        attributes.addElement(att4);
        attributes.addElement(att5);
        attributes.addElement(att6);
        attributes.addElement(att7);
        attributes.addElement(att8);
        attributes.addElement(att9);
        attributes.addElement(att10);
        attributes.addElement(att11);
        Instances dataset = new Instances("Test-dataset", attributes, 0);
        dataset.setClassIndex(10); 
        
        
        for(int i=0;i<questionID.size();i++){
            double []values=new double[11];
            for(int m=0;m<11;m++){values[m]=0;}
            int whitewordcount=0;
            whitewordcount=questionDAO.getHitWhiteWordNum(questionID.get(i).getId());
            if(whitewordcount!=0){
                List<QuestionWhiteWord> questionwhiteword=questionDAO.getHitQuestionWhiteWord(questionID.get(i).getId());
                for(int j=0;j<questionwhiteword.size();j++){
                    values[getAttIndex(questionwhiteword.get(j).getWordId())-1]++;
                }
                for(int m=0;m<11;m++){values[m]=values[m]/whitewordcount;}
            }
            values[10]=questionID.get(i).getType()-1;
            Instance inst = new Instance(1.0, values);
            dataset.add(inst); 

    }
        return dataset;
    
}
    
    public void evaluateClassifyMethod() throws Exception{
                
                Instances data1 =getInstances();
                Evaluation eval = new Evaluation(data1);
                J48 tree = new J48();
                eval.crossValidateModel(tree, data1, 10, new Random(1));
                System.out.println("决策树准确率：");
                System.out.println(eval.toSummaryString("\nResults\n\n", false));

                
                Instances data2 =getInstances();
                Evaluation eval2 = new Evaluation(data2);
                NaiveBayes bayes = new NaiveBayes();
                eval2.crossValidateModel(bayes, data2, 10, new Random(1));
                System.out.println("贝叶斯准确率：");
                System.out.println(eval2.toSummaryString("\nResults\n\n", false));

                
                Instances data3 =getInstances();
                Evaluation eval3 = new Evaluation(data3);
                IBk ibk = new IBk(3);
                eval3.crossValidateModel(ibk, data3, 10, new Random(1));
                System.out.println("KNN准确率：");
                System.out.println(eval3.toSummaryString("\nResults\n\n", false));
    }
    public String getClassName(int type){
        String temp=null;
        switch(type){
        case 1: temp="法律政策";break;
        case 2: temp="位置交通";break;
        case 3: temp="风水"; break;
        case 4: temp="房价"; break;
        case 5: temp="楼层"; break;
        case 6: temp="户型";break;
        case 7: temp="小区配套"; break;
        case 8: temp="贷款"; break;
        case 9: temp="买房时机"; break;
        case 10: temp="开发商"; break;
        }
        return temp;
    }
}