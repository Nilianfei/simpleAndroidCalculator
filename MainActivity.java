package com.example.al.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static java.lang.Character.isDigit;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_point;
    Button btn_clear;
    Button btn_del;
    Button btn_add;
    Button btn_minus;
    Button btn_multiply;
    Button btn_divide;
    Button btn_equal;
    TextView tv_result;
    TextView tv_expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_0 = (Button) findViewById(R.id.btn_0) ;
        btn_1 = (Button) findViewById(R.id.btn_1) ;
        btn_2 = (Button) findViewById(R.id.btn_2) ;
        btn_3 = (Button) findViewById(R.id.btn_3) ;
        btn_4 = (Button) findViewById(R.id.btn_4) ;
        btn_5 = (Button) findViewById(R.id.btn_5) ;
        btn_6 = (Button) findViewById(R.id.btn_6) ;
        btn_7 = (Button) findViewById(R.id.btn_7) ;
        btn_8 = (Button) findViewById(R.id.btn_8) ;
        btn_9 = (Button) findViewById(R.id.btn_9) ;
        btn_point = (Button) findViewById(R.id.btn_point) ;
        btn_clear = (Button) findViewById(R.id.btn_clear) ;
        btn_del = (Button) findViewById(R.id.btn_del) ;
        btn_add = (Button) findViewById(R.id.btn_add) ;
        btn_minus = (Button) findViewById(R.id.btn_minus) ;
        btn_multiply = (Button) findViewById(R.id.btn_multiply) ;
        btn_divide = (Button) findViewById(R.id.btn_divide) ;
        btn_equal = (Button) findViewById(R.id.btn_equal) ;
        tv_expression=(TextView)findViewById(R.id.tv_expression);
        tv_result=(TextView)findViewById(R.id.tv_result);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }
    public void onClick(View v){
        String str=tv_expression.getText().toString();
        boolean flag=true;
        switch (v.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                tv_expression.setText(str+((Button)v).getText());break;
            case R.id.btn_point:
                if(str!=null&&!str.equals("")) {
                    if ('0' <= str.charAt(str.length() - 1) && '9' >= str.charAt(str.length() - 1))
                        tv_expression.setText(str + ".");
                }
                break;
            case R.id.btn_add:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if(str!=null&&!str.equals("")) {
                    if (('0' <= str.charAt(str.length() - 1) && '9' >= str.charAt(str.length() - 1)) || '.' == str.charAt(str.length() - 1))
                        tv_expression.setText(str + ((Button) v).getText());
                }
                break;
            case R.id.btn_del:
                if(str!=null&&!str.equals(""))tv_expression.setText(str.substring(0,str.length()-1));
                break;
            case R.id.btn_clear:
                tv_expression.setText("");
                tv_result.setText("");
                break;
            case R.id.btn_equal:
                tv_expression.setText(tv_result.getText().toString());
                tv_result.setText("");
                flag=false;
                break;
        }
        if(flag)
            tv_result.setText(getResult(intosuf(tv_expression.getText().toString())));
    }
    public String getResult(String sufstr){
        if(sufstr==null||sufstr.equals(""))return "";
        int strlen=sufstr.length();
        Stack<String> st=new Stack<String>();
        for(int i=0;i<strlen;i++){
            String str="";
            if(isDigit(sufstr.charAt(i))){
                while(i<strlen&&(isDigit(sufstr.charAt(i))||sufstr.charAt(i)=='.')){
                    str=str+String.valueOf(sufstr.charAt(i));
                    i++;
                }
                i--;
            }
            else str=str+String.valueOf(sufstr.charAt(i));
            double a=0,b=0;
            switch(str){
                case "#":break;
                case "+":
                    if(!st.empty())
                        b=Double.parseDouble(st.pop());
                    if(!st.empty())
                        a=Double.parseDouble(st.pop());
                    st.push(Double.toString(a+b));
                    break;
                case "-":
                    if(!st.empty())
                        b=Double.parseDouble(st.pop());
                    if(!st.empty())
                        a=Double.parseDouble(st.pop());
                    st.push(Double.toString(a-b));
                    break;
                case "*":
                    if(!st.empty())
                        b=Double.parseDouble(st.pop());
                    if(!st.empty())
                        a=Double.parseDouble(st.pop());
                    st.push(Double.toString(a*b));
                    break;
                case "/":
                    if(!st.empty())
                        b=Double.parseDouble(st.pop());
                    if(!st.empty())
                        a=Double.parseDouble(st.pop());
                    st.push(Double.toString(a/b));
                    break;
                default:st.push(str);
            }
        }
        if(!st.empty()) return doubleTrans(Double.parseDouble(st.pop()));
        else return "";
    }
    public String intosuf(String instr){
        if(instr==null||instr.equals(""))return "";
        String sufstr="";
        String str=null;
        Stack<String> st=new Stack<String>();
        int strlen=instr.length();
        if(instr.charAt(strlen-1)!='.'&&!isDigit(instr.charAt(strlen-1))){
            instr=instr.substring(0, strlen-1);
            strlen--;
        }
        for(int i=0;i<strlen;i++){
            if(isDigit(instr.charAt(i))){str="";
                while(i<strlen&&(isDigit(instr.charAt(i))||instr.charAt(i)=='.')){
                    str=str+String.valueOf(instr.charAt(i));
                    i++;
                }
                i--;}
            else str=String.valueOf(instr.charAt(i));
            switch(str){
                case "*":
                case "/":if(st.empty()){st.push(str);break;}
                else {
                    if(st.peek().equals("*")||st.peek().equals("/"))sufstr=sufstr+st.pop();
                    st.push(str);break;}
                case "+":
                case "-":if(st.empty()){st.push(str);break;}
                else {
                    if(st.peek().equals("*")||st.peek().equals("/"))sufstr=sufstr+st.pop();
                    if(!st.empty()&&(st.peek().equals("+")||st.peek().equals("-")))sufstr=sufstr+st.pop();
                    st.push(str);break;
                }
                default:sufstr=sufstr+str+"#";break;
            }
        }
        while(!st.empty())sufstr=sufstr+st.pop();
        return sufstr;
    }
    public  String doubleTrans(double num){
        if(num % 1.0 == 0){
            return String.valueOf((long)num);
        }
        return String.valueOf(num);
    }
}