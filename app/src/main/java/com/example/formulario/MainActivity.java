package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArraySet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    LinearLayout principal;
    ScrollView scrollLayout;
    EditText etTitulo, etDesc;
    Button addQuestion;
    Button delQuestion;
    Button finalizar;
    int contaId;
    int numPerguntas;
    int temtitulo;
    int[] a;
    int[] b;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        principal = (LinearLayout) findViewById(R.id.mainLayout);
        scrollLayout = (ScrollView) findViewById(R.id.scrollView);
        etTitulo = (EditText) findViewById(R.id.etTitle);
        etDesc = (EditText) findViewById(R.id.etDes);
        addQuestion = (Button) findViewById(R.id.btnAdd);
        delQuestion = (Button) findViewById(R.id.btnDel);
        finalizar = (Button) findViewById(R.id.btnFinalizar);
        temtitulo = 0;
        numPerguntas = 0;
        a = new int[50];
        b = new int[50];
        i = 0;

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        scrollLayout.setVerticalScrollBarEnabled(false);

        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etTitulo.getText().toString().trim().isEmpty()){
                    temtitulo = 0;
                } else {
                    temtitulo = 1;
                }
            }
        };
        etTitulo.addTextChangedListener(textWatcher);

        adiciona(principal);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    public void adiciona(View v){
        numPerguntas++;

        LinearLayout subPrincipal = new LinearLayout(MainActivity.this);
        subPrincipal.setId(contaId); contaId ++;
        subPrincipal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        subPrincipal.setOrientation(LinearLayout.VERTICAL);

        LinearLayout novoLayout = new LinearLayout(MainActivity.this);
        novoLayout.setId(contaId); contaId ++;
        novoLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout horizontalUm = new LinearLayout(MainActivity.this);
            horizontalUm.setId(contaId); contaId++;
            horizontalUm.setOrientation(LinearLayout.HORIZONTAL);
            horizontalUm.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                TextView tV = new TextView(MainActivity.this);
                tV.setId(contaId); contaId ++;
                tV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                tV.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                tV.setText(numPerguntas + ".");
                horizontalUm.addView(tV);
                EditText eT = new EditText(MainActivity.this);
                eT.setId(contaId); contaId ++;
                eT.setText("");
                eT.setHint("Adicione uma pergunta");
                eT.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                horizontalUm.addView(eT);
                TextView tvinv = new TextView(MainActivity.this);
                tvinv.setId(contaId); contaId ++;
                tvinv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                tvinv.setVisibility(View.GONE);
                horizontalUm.addView(tvinv);
            novoLayout.addView(horizontalUm);

            int num = (int) Double.parseDouble(tV.getText().toString());

        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvinv.setText(eT.getText().toString());
                if (tvinv.getText().toString().trim().isEmpty()){
                    a[num - 1] = 0;
                } else {
                    a[num - 1] = 1;
                }
            }
        };
        eT.addTextChangedListener(textWatcher);


        LinearLayout obrigatorio = new LinearLayout(MainActivity.this);
        obrigatorio.setId(contaId); contaId ++;
        obrigatorio.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout horObg = new LinearLayout(MainActivity.this);
            horObg.setId(contaId); contaId ++;
            horObg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            horObg.setOrientation(LinearLayout.HORIZONTAL);
                Switch switchObg = new Switch(MainActivity.this);
                switchObg.setId(contaId); contaId++;
                switchObg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                switchObg.setText("  Opcional ");
                int defaultTextColor = switchObg.getTextColors().getDefaultColor();
                horObg.addView(switchObg);
                TextView tvObg = new TextView(MainActivity.this);
                tvObg.setId(contaId); contaId++;
                tvObg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                tvObg.setText(" Obrigatória");
                int defaultTextCinza = tvObg.getTextColors().getDefaultColor();
                horObg.addView(tvObg);
                switchObg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked) {
                            tvObg.setTextColor(Color.parseColor("#FB528B"));
                        } else {
                            tvObg.setTextColor(defaultTextCinza);
                        }
                    }
                });
            obrigatorio.addView(horObg);

        LinearLayout novoLayout2 = new LinearLayout(MainActivity.this);
        novoLayout2.setId(contaId); contaId ++;
        novoLayout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            RadioGroup radioGrupo = new RadioGroup(MainActivity.this);
            radioGrupo.setId(contaId); contaId ++;
            radioGrupo.setOrientation(RadioGroup.HORIZONTAL);
            radioGrupo.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT));
                RadioButton radioTexto = new RadioButton(MainActivity.this);
                radioTexto.setId(contaId); contaId ++;
                radioTexto.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
                radioTexto.setText("Texto");
                radioGrupo.addView(radioTexto);
                RadioButton radioMulti = new RadioButton(MainActivity.this);
                radioMulti.setId(contaId); contaId ++;
                radioMulti.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
                radioMulti.setText("Múltipla Escolha");
                radioGrupo.addView(radioMulti);
                RadioButton radioCheck = new RadioButton(MainActivity.this);
                radioCheck.setId(contaId); contaId ++;
                radioCheck.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
                radioCheck.setText("Caixas de Seleção");
                radioGrupo.addView(radioCheck);
            novoLayout2.addView(radioGrupo);



        LinearLayout novoLayout3 = new LinearLayout(MainActivity.this);
        novoLayout3.setId(contaId); contaId ++;
        novoLayout3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            EditText etResposta = new EditText(MainActivity.this);
            etResposta.setId(contaId); contaId ++;
            etResposta.setText("A resposta do usuário virá aqui.");
            etResposta.setEnabled(false);
            etResposta.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        novoLayout3.addView(etResposta);
        novoLayout3.setVisibility(View.GONE);

        LinearLayout novoLayout4 = new LinearLayout(MainActivity.this);
        novoLayout4.setId(contaId); contaId ++;
        novoLayout4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        novoLayout4.setOrientation(LinearLayout.VERTICAL);
            LinearLayout linearRadio = new LinearLayout(MainActivity.this);
            linearRadio.setId(contaId); contaId ++;
            linearRadio.setOrientation(LinearLayout.VERTICAL);
            linearRadio.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                LinearLayout horRadio = new LinearLayout(MainActivity.this);
                horRadio.setId(contaId); contaId ++;
                horRadio.setOrientation(LinearLayout.HORIZONTAL);
                horRadio.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    RadioButton radioNull = new RadioButton(MainActivity.this);
                    radioNull.setId(contaId); contaId ++;
                    radioNull.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    radioNull.setText("");
                    radioNull.setClickable(false);
                    horRadio.addView(radioNull);
                    EditText editRadio = new EditText(MainActivity.this);
                    editRadio.setId(contaId); contaId++;
                    editRadio.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    editRadio.setHint("Clique aqui");
                    editRadio.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                    horRadio.addView(editRadio);
                linearRadio.addView(horRadio);
            novoLayout4.addView(linearRadio);
            LinearLayout horizontalDois = new LinearLayout(MainActivity.this);
            horizontalDois.setId(contaId); contaId++;
            horizontalDois.setOrientation(LinearLayout.HORIZONTAL);
            horizontalDois.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                Button adicionaRadio = new Button(MainActivity.this);
                adicionaRadio.setId(contaId); contaId++;
                adicionaRadio.setLayoutParams(new LinearLayout.LayoutParams(280, LinearLayout.LayoutParams.WRAP_CONTENT));
                adicionaRadio.setText("Adicionar");
                adicionaRadio.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        LinearLayout horRadio2 = new LinearLayout(MainActivity.this);
                        horRadio2.setId(contaId); contaId ++;
                        horRadio2.setOrientation(LinearLayout.HORIZONTAL);
                        horRadio2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            RadioButton radioNull2 = new RadioButton(MainActivity.this);
                            radioNull2.setId(contaId); contaId ++;
                            radioNull2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            radioNull2.setText("");
                            radioNull2.setClickable(false);
                            horRadio2.addView(radioNull2);
                            EditText editRadio2 = new EditText(MainActivity.this);
                            editRadio2.setId(contaId); contaId++;
                            editRadio2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            editRadio2.setHint("Clique aqui");
                            editRadio2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                            horRadio2.addView(editRadio2);
                        linearRadio.addView(horRadio2);

                        scrollLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                scrollLayout.fullScroll(ScrollView.FOCUS_DOWN);
                            }
                        });
                    }
                });
                horizontalDois.addView(adicionaRadio);
                Button remRadio = new Button(MainActivity.this);
                remRadio.setId(contaId); contaId++;
                remRadio.setLayoutParams(new LinearLayout.LayoutParams(280, LinearLayout.LayoutParams.WRAP_CONTENT));
                remRadio.setText("Deletar");
                remRadio.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        if(linearRadio.getChildCount() != 1) {
                            linearRadio.removeViewAt(linearRadio.getChildCount() - 1);
                        } else {
                            toast();
                        }
                    }
                });
                horizontalDois.addView(remRadio);
            novoLayout4.addView(horizontalDois);
            novoLayout4.setVisibility(View.GONE);

            LinearLayout novoLayout5 = new LinearLayout(MainActivity.this);
            novoLayout5.setId(contaId); contaId ++;
            novoLayout5.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            novoLayout5.setOrientation(LinearLayout.VERTICAL);
                LinearLayout linearCheck = new LinearLayout(MainActivity.this);
                linearCheck.setId(contaId); contaId ++;
                linearCheck.setOrientation(LinearLayout.VERTICAL);
                linearCheck.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    LinearLayout horCheck = new LinearLayout(MainActivity.this);
                    horCheck.setId(contaId); contaId ++;
                    horCheck.setOrientation(LinearLayout.HORIZONTAL);
                    horCheck.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        CheckBox checkNull = new CheckBox(MainActivity.this);
                        checkNull.setId(contaId); contaId ++;
                        checkNull.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        checkNull.setText("");
                        checkNull.setClickable(false);
                        horCheck.addView(checkNull);
                        EditText editCheck = new EditText(MainActivity.this);
                        editCheck.setId(contaId); contaId++;
                        editCheck.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        editCheck.setHint("Clique aqui");
                        editCheck.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                        horCheck.addView(editCheck);
                    linearCheck.addView(horCheck);
                novoLayout5.addView(linearCheck);
                LinearLayout horizontalTres = new LinearLayout(MainActivity.this);
                horizontalTres.setId(contaId); contaId++;
                horizontalTres.setOrientation(LinearLayout.HORIZONTAL);
                horizontalTres.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    Button adicionaCheck = new Button(MainActivity.this);
                    adicionaCheck.setId(contaId); contaId++;
                    adicionaCheck.setLayoutParams(new LinearLayout.LayoutParams(280, LinearLayout.LayoutParams.WRAP_CONTENT));
                    adicionaCheck.setText("Adicionar");
                    adicionaCheck.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v){
                            LinearLayout horCheck2 = new LinearLayout(MainActivity.this);
                                horCheck2.setId(contaId); contaId ++;
                                horCheck2.setOrientation(LinearLayout.HORIZONTAL);
                                horCheck2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                    CheckBox checkNull2 = new CheckBox(MainActivity.this);
                                    checkNull2.setId(contaId); contaId ++;
                                    checkNull2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                    checkNull2.setText("");
                                    checkNull2.setClickable(false);
                                    horCheck2.addView(checkNull2);
                                    EditText editCheck2 = new EditText(MainActivity.this);
                                    editCheck2.setId(contaId); contaId++;
                                    editCheck2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                    editCheck2.setHint("Clique aqui");
                                    editCheck2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                                    horCheck2.addView(editCheck2);
                                linearCheck.addView(horCheck2);

                            scrollLayout.post(new Runnable() {
                                @Override
                                public void run() {
                                    scrollLayout.fullScroll(ScrollView.FOCUS_DOWN);
                                }
                            });
                        }
                    });
                    horizontalTres.addView(adicionaCheck);
                    Button remCheck = new Button(MainActivity.this);
                    remCheck.setId(contaId); contaId++;
                    remCheck.setLayoutParams(new LinearLayout.LayoutParams(280, LinearLayout.LayoutParams.WRAP_CONTENT));
                    remCheck.setText("Deletar");
                    remCheck.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v){
                            if(linearCheck.getChildCount() != 1) {
                                linearCheck.removeViewAt(linearCheck.getChildCount() - 1);
                            } else {
                                toast();
                            }
                        }
                    });
                    horizontalTres.addView(remCheck);
                novoLayout5.addView(horizontalTres);
                novoLayout5.setVisibility(View.GONE);

        LinearLayout novoLayout6 = new LinearLayout(MainActivity.this);
        novoLayout6.setId(contaId); contaId ++;
        novoLayout6.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        novoLayout6.setVisibility(View.INVISIBLE);
            TextView space = new TextView(MainActivity.this);
            space.setId(contaId); contaId ++;
            space.setText("Espaço");
            space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            novoLayout6.addView(space);

        b[num-1] = 0;

        radioTexto.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                radioTexto.setTextColor(Color.parseColor("#FB528B"));
                radioMulti.setTextColor(defaultTextColor);
                radioCheck.setTextColor(defaultTextColor);

                novoLayout3.setVisibility(View.VISIBLE);
                novoLayout4.setVisibility(View.GONE);
                novoLayout5.setVisibility(View.GONE);

                b[num-1] = 1;
            }
        });

        radioMulti.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                radioTexto.setTextColor(defaultTextColor);
                radioMulti.setTextColor(Color.parseColor("#FB528B"));
                radioCheck.setTextColor(defaultTextColor);

                novoLayout3.setVisibility(View.GONE);
                novoLayout4.setVisibility(View.VISIBLE);
                novoLayout5.setVisibility(View.GONE);

                b[num-1] = 1;
            }
        });

        radioCheck.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                radioTexto.setTextColor(defaultTextColor);
                radioMulti.setTextColor(defaultTextColor);
                radioCheck.setTextColor(Color.parseColor("#FB528B"));

                novoLayout3.setVisibility(View.GONE);
                novoLayout4.setVisibility(View.GONE);
                novoLayout5.setVisibility(View.VISIBLE);

                b[num-1] = 1;
            }
        });

        subPrincipal.addView(novoLayout);
        subPrincipal.addView(obrigatorio);
        subPrincipal.addView(novoLayout2);
        subPrincipal.addView(novoLayout3);
        subPrincipal.addView(novoLayout4);
        subPrincipal.addView(novoLayout5);
        subPrincipal.addView(novoLayout6);
        principal.addView(subPrincipal);

        scrollLayout.post(new Runnable() {
            @Override
            public void run() {
                scrollLayout.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public void deleta(View v){
        if(numPerguntas > 1){
            numPerguntas -= 1;
            View view = principal.getChildAt(numPerguntas);
            principal.removeView(view);
        } else{
            toast();
        }
    }

    public void finaliza(View v){
        int conta = 0;
        if(temtitulo == 0){
            Toast.makeText(this, "Por favor, dê um título ao formulário", Toast.LENGTH_SHORT).show();
            etTitulo.requestFocus();
        } else {
            for (i = 0; i < numPerguntas; i++) {
                int iMaisUm = i + 1;
                if (a[i] == 0 || b[i] == 0) {
                    Toast.makeText(this, "Nem todos os campos foram preenchidos na questão " + iMaisUm, Toast.LENGTH_SHORT).show();
                    principal.getChildAt(i).requestFocus();
                    break;
                } else {
                    conta++;
                }
            }
            if(conta == numPerguntas){
                Toast.makeText(this, "Formulário finalizado!", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, TelaInicial.class);
                        startActivity(intent);
                    }
                }, 1000);
            }
        }
    }

    public void toast(){
        if(delQuestion.isPressed()){
            Toast.makeText(this, "É necessário haver ao menos uma questão", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "É necessário haver ao menos uma alternativa", Toast.LENGTH_SHORT).show();
        }
    }

}