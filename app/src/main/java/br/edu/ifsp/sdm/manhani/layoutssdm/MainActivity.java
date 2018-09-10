package br.edu.ifsp.sdm.manhani.layoutssdm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String ESTADO_NOTIFICACAO_CHECKBOX = "ESTADO_NOTIFICACAO_CHECKBOX";
    private final static String ESTADO_NOTIFICACAO_RADIO = "ESTADO_NOTIFICACAO_RADIO";
    private CheckBox notificacoesCheckbox;
    private RadioGroup notificacoesRadioGroup;
    private EditText nomeEditText;
    private LinearLayout foneLinearLayout;
    private LinearLayout emailLinearLayout;
    private ArrayList<View> telefoneArrayList;
    private ArrayList<View> emailArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_activity_layout);

        notificacoesCheckbox = findViewById(R.id.notificacoesCheckbox);
        notificacoesRadioGroup = findViewById(R.id.notificacoesRadioGroup);
        nomeEditText = findViewById(R.id.nomeEditText);
        foneLinearLayout = findViewById(R.id.foneLinearLayout);
        emailLinearLayout = findViewById(R.id.emailLinearLayout);

        telefoneArrayList = new ArrayList<>();
        emailArrayList = new ArrayList<>();
//        notificacoesCheckbox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (((CheckBox) v).isChecked()) {
//                    notificacoesRadioGroup.setVisibility(View.VISIBLE);
//                } else {
//                    notificacoesRadioGroup.setVisibility(View.GONE);
//                }
//            }
//        });


        notificacoesCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    notificacoesRadioGroup.setVisibility(View.VISIBLE);
                } else {
                    notificacoesRadioGroup.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ESTADO_NOTIFICACAO_CHECKBOX, notificacoesCheckbox.isChecked());
        outState.putInt(ESTADO_NOTIFICACAO_RADIO, notificacoesRadioGroup.getCheckedRadioButtonId());

        outState.putInt("emailSize", emailArrayList.size());
        for (int i = 0; i < emailArrayList.size(); i++) {
            List<View> listaFilhos = emailArrayList.get(i).getTouchables();
            for (int j = 0; j < listaFilhos.size(); j++) {
                if (listaFilhos.get(j) instanceof EditText) {
                    EditText et = (EditText) listaFilhos.get(j);
                    outState.putString("email" + j, et.getText().toString());
                }
            }
        }

        outState.putInt("foneSize", telefoneArrayList.size());
        for (int i = 0; i < telefoneArrayList.size(); i++) {
            List<View> listaFilhos = telefoneArrayList.get(i).getTouchables();
            for (int j = 0; j < listaFilhos.size(); j++) {
                if (listaFilhos.get(j) instanceof EditText) {
                    EditText et = (EditText) listaFilhos.get(j);
                    outState.putString("fone" + i, et.getText().toString());
                } else if (listaFilhos.get(j) instanceof Spinner) {
                    Spinner s = (Spinner) listaFilhos.get(j);
                    outState.putInt("tipoFone" + i, s.getSelectedItemPosition());
                }
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            notificacoesRadioGroup.check(savedInstanceState.getInt(ESTADO_NOTIFICACAO_RADIO, -1));
            notificacoesCheckbox.setChecked(savedInstanceState.getBoolean(ESTADO_NOTIFICACAO_CHECKBOX, false));

            Integer emailSize = savedInstanceState.getInt("emailSize", 0);
            for (int i = 0; i < emailSize; i++) {
                LayoutInflater inflater = getLayoutInflater();
                View novo = inflater.inflate(R.layout.novo_email_layout, null);
                List<View> listaFilhos = novo.getTouchables();
                for (int j = 0; j < listaFilhos.size(); j++) {
                    if (listaFilhos.get(j) instanceof EditText) {
                        EditText et = (EditText) listaFilhos.get(j);
                        et.setText(savedInstanceState.getString("email" + j));
                    }
                }
                emailArrayList.add(novo);
                emailLinearLayout.addView(novo);
            }

            Integer foneSize = savedInstanceState.getInt("foneSize", 0);
            for (int i = 0; i < foneSize; i++) {
                LayoutInflater inflater = getLayoutInflater();
                View novo = inflater.inflate(R.layout.novo_telefone_layout, null);
                List<View> listaFilhos = novo.getTouchables();
                for (int j = 0; j < listaFilhos.size(); j++) {
                    if (listaFilhos.get(j) instanceof EditText) {
                        EditText et = (EditText) listaFilhos.get(j);
                        et.setText(savedInstanceState.getString("fone" + i));
                    } else if (listaFilhos.get(j) instanceof Spinner) {
                        Spinner s = (Spinner) listaFilhos.get(j);
                        s.setSelection(savedInstanceState.getInt("tipoFone" + i, -1));
                    }
                }
                telefoneArrayList.add(novo);
                foneLinearLayout.addView(novo);
            }
        }
    }

    public void limparFormulario(View view) {
        notificacoesCheckbox.setChecked(false);
        notificacoesRadioGroup.clearCheck();
        nomeEditText.setText(null);
        nomeEditText.requestFocus();
        telefoneArrayList.clear();
        emailArrayList.clear();
        emailLinearLayout.removeAllViews();
        foneLinearLayout.removeAllViews();
    }

    public void salvarFormulario(View view) {
        //TODO implementar
    }

    public void adicionarTelefone(View view) {
        if (view.getId() == R.id.addFoneButton) {
            LayoutInflater inflater = getLayoutInflater();
            View novo = inflater.inflate(R.layout.novo_telefone_layout, null);
            telefoneArrayList.add(novo);
            foneLinearLayout.addView(novo);
        }
    }

    public void adicionarEmail(View view) {
        if (view.getId() == R.id.addEmailButton) {
            LayoutInflater inflater = getLayoutInflater();
            View novo = inflater.inflate(R.layout.novo_email_layout, null);
            emailArrayList.add(novo);
            emailLinearLayout.addView(novo);
        }
    }
}
