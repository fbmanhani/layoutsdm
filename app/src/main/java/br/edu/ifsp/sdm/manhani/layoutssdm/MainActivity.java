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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static String ESTADO_NOTIFICACAO_CHECKBOX = "ESTADO_NOTIFICACAO_CHECKBOX";
    private final static String ESTADO_NOTIFICACAO_RADIO = "ESTADO_NOTIFICACAO_RADIO";
    private CheckBox notificacoesCheckbox;
    private RadioGroup notificacoesRadioGroup;
    private EditText nomeEditText;
    private EditText emailEditText;
    //    private EditText foneEditText;
    private LinearLayout foneLinearLayout;
    private ArrayList<View> telefoneArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_activity_layout);

        notificacoesCheckbox = findViewById(R.id.notificacoesCheckbox);
        notificacoesRadioGroup = findViewById(R.id.notificacoesRadioGroup);
        nomeEditText = findViewById(R.id.nomeEditText);
        emailEditText = findViewById(R.id.emailEditText);
//        foneEditText = findViewById(R.id.foneEditText);
        foneLinearLayout = findViewById(R.id.foneLinearLayout);

        telefoneArrayList = new ArrayList<>();
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
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            notificacoesRadioGroup.check(savedInstanceState.getInt(ESTADO_NOTIFICACAO_RADIO, -1));
            notificacoesCheckbox.setChecked(savedInstanceState.getBoolean(ESTADO_NOTIFICACAO_CHECKBOX, false));
        }
    }

    public void limparFormulario(View view) {
        notificacoesCheckbox.setChecked(false);
        notificacoesRadioGroup.clearCheck();
        nomeEditText.setText(null);
        emailEditText.setText(null);
//        foneEditText.setText(null);
        nomeEditText.requestFocus();
    }

    public void salvarFormulario(View view) {
    }

    public void adicionarTelefone(View view) {
        if (view.getId() == R.id.addFoneButton) {
            LayoutInflater inflater = getLayoutInflater();

            View novo = inflater.inflate(R.layout.novo_telefone_layout, null);
            telefoneArrayList.add(novo);
            foneLinearLayout.addView(novo);
        }
    }
}
