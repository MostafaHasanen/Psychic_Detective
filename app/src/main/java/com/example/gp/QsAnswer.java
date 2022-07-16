package com.example.gp;

public class QsAnswer {
//Attributes
    int QSNum;
    int PageNum;
    String Ans;
    int AnsScore;
    int indexInArr;
    static  int Count=0;

//Constructors
    public QsAnswer( ) {
        QSNum = 0;
        PageNum = 0;
        AnsScore = 0;
        Ans="";
    }

    public QsAnswer(int QN, int PN,int S,String A,int Ind) {
        QSNum = QN;
        PageNum = PN;
        AnsScore=S;
        Ans = A;
        indexInArr=Ind;
        Count++;
    }


//______________Get&set_____________________
    public int getQSNum() {
        return QSNum;
    }

    public void setQSNum(int QSNum) {
        this.QSNum = QSNum;
    }

    public int getPageNum() {
        return PageNum;
    }

    public void setPageNum(int pageNum) {
        PageNum = pageNum;
    }

    public String getAns() {
        return Ans;
    }

    public void setAns(String ans) {
        Ans = ans;
    }

    public int getAnsScore() {
        return AnsScore;
    }

    public void setAnsScore(int ansScore) {
        AnsScore = ansScore;
    }
    //___________________
    public static int Total_Score(QsAnswer [] S) {
        int Score=0;
        for (int i = 0; i < S.length; i++)
        {
            Score+=S[i].getAnsScore();

        }
        return Score;
    }
        public static QsAnswer Search ( int index, QsAnswer[] Q){
            for (int i = 0; i < Q.length; i++) {
                if (Q[i]!= null) {
                    if (index == Q[i].QSNum) {
                        return Q[i];
                    }
                }
                else{
                    return null;
                }


            }
            return null;
        }

}
