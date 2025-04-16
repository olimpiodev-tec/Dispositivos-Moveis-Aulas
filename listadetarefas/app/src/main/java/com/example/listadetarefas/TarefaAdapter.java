package com.example.listadetarefas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.List;

public class TarefaAdapter extends BaseAdapter {

    private Context context;
    private List<Tarefa> tarefas;

    public TarefaAdapter(Context context, List<Tarefa> tarefas) {
        this.context = context;
        this.tarefas = tarefas;
    }

    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context)
                .inflate(R.layout.tarefa_item, parent, false);

        Tarefa tarefa = tarefas.get(position);

        TextView tvTitulo = convertView.findViewById(R.id.tvTitulo);
        TextView tvDescricao = convertView.findViewById(R.id.tvDescricao);
        TextView tvStatus = convertView.findViewById(R.id.tvStatus);

        tvTitulo.setText(tarefa.getTitulo());
        tvDescricao.setText(tarefa.getDescricao());

        if (tarefa.isConcluido()) {
            tvStatus.setTextColor(ContextCompat.getColor(context, R.color.green));
            tvStatus.setText("Finalizada");
        } else {
            tvStatus.setTextColor(ContextCompat.getColor(context, R.color.yellow));
            tvStatus.setText("Em andamento");
        }

        return convertView;
    }
}
