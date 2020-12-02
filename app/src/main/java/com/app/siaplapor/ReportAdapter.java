package com.app.siaplapor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {

    private ArrayList<Report> reportList;

    public ReportAdapter(ArrayList<Report> reportList) {
        this.reportList = reportList;
    }

    @NonNull
    @Override 
    public ReportAdapter.ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_report, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ReportViewHolder holder, int position) {
        holder.txtName.setText(reportList.get(position).getName());
        holder.txtDate.setText(reportList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return (reportList != null) ? reportList.size() : 0;
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtDate;
        
        public ReportViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.name);
            txtDate = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
