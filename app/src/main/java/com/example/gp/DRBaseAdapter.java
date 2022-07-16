package com.example.gp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DRBaseAdapter extends BaseAdapter {
    String [] DName;
    String [] DDegree;
    String [] DMail;
    String [] DPhoneNo;
   LayoutInflater inflater;
    Context CT;
    public DRBaseAdapter(Context C , String [] Names, String [] Degrees, String [] Mails, String [] PhoneNos) {
        CT=C;
        DName=Names;
        DDegree= Degrees;
        DMail=Mails;
        DPhoneNo=PhoneNos;
        inflater=LayoutInflater.from(C);

    }

    @Override
    public int getCount() {
        return DName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.doctor_list,null);
        TextView NameT= (TextView)view.findViewById(R.id.DoctorName);
        TextView DegreeT= (TextView)view.findViewById(R.id.doctorDegree);
        TextView PhoneT= (TextView)view.findViewById(R.id.DoctorPhoneNo);
        TextView MailT= (TextView)view.findViewById(R.id.DoctorEmail);
        NameT.setText(DName[i]);
        DegreeT.setText(DDegree[i]);
        PhoneT.setText(DPhoneNo[i]);
        MailT.setText(DMail[i]);


        return view;
    }
}
