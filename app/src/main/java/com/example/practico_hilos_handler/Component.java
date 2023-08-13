package com.example.practico_hilos_handler;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Component {
    private  ViewGroup layout ;
    // Mapa para almacenar componentes por ID personalizado
    private Map<String, View> viewMap = new HashMap<>();
    private List<ElementInfo> elements = new ArrayList<>();
    public Component(ViewGroup layout) {
        this.layout = layout;
    }
    public void render(){
        //Renderizamos los elementos en el orden en el que los seteamos
        for (ElementInfo elementInfo: elements) {
            View view = elementInfo.createView(layout.getContext());
            layout.addView(view);
            viewMap.put(elementInfo.getID(),view);
        }
    }
    public void addTextView(String id, String text){
        elements.add(new ElementInfo(
                ElementInfo.TYPE_TEXT_VIEW,
                text,
                id
        ));
    }
    // Agrega Botones a la lista, pasando como parametro una funcion que sera su Clicklistener
    public void addButton(String id,String text,View.OnClickListener clickListener){
        text = (text == null) ? "Procesar":text;
        ElementInfo elementInfo =  new ElementInfo(
                ElementInfo.TYPE_BUTTON,
                text,
                id);
        elementInfo.setClickListener(clickListener);
        elements.add(elementInfo);
    }
    public void addEditText(String id){
        elements.add(new ElementInfo(ElementInfo.TYPE_EDIT_TEXT,
                "Input",
                id
        ));
    }
    public void addEditText(String id, boolean b){
        elements.add(new ElementInfo(ElementInfo.TYPE_EDIT_TEXT,
                "Input",
                id,
                b
        ));
    }
    public View getViewById(String uniqueId) {
        return viewMap.get(uniqueId);
    }
    private static class ElementInfo {
        static final int TYPE_TEXT_VIEW = 0;
        static final int TYPE_BUTTON = 1;
        static final int TYPE_EDIT_TEXT = 2;
        int elementType;
        private View.OnClickListener clickListener;
        String content;
        String id;
        boolean isNumber=false;

        ElementInfo(int elementType, String content, String id) {
            this.elementType = elementType;
            this.content = content;
            this.id = id;
        }
        ElementInfo(int elementType, String content, String id, boolean isNumber) {
            this.elementType = elementType;
            this.content = content;
            this.id = id;
            this.isNumber = isNumber;
        }

        View createView(Context context) {
            switch (elementType) {
                case TYPE_TEXT_VIEW:
                    return createTextView(context);
                case TYPE_BUTTON:
                    return createButton(context);
                case TYPE_EDIT_TEXT:
                    return createEditText(context);
                default:
                    return null;
            }
        }
        private Button createButton(Context context){
            Button button = new Button(context);
            button.setText(content);
            button.setOnClickListener(this.clickListener);
            return button;
        }
        private TextView createTextView(Context context){
            TextView textView = new TextView(context);
            textView.setLayoutParams(new ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            textView.setText(content);
            return textView;
        }
        private EditText createEditText(Context context) {
            EditText editText = new EditText(context);
            editText.setLayoutParams(new ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            if(isNumber){
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
            editText.setHint(content);
            return editText;
        }
        public void setClickListener(View.OnClickListener clickListener){this.clickListener = clickListener;}
        public String getID(){return this.id;}

    }
}