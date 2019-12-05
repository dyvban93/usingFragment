package com.projects.bakota.composantesgraphiques;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button suiv;
    EditText mNomE, mPrenomE, mDate;
    Spinner lang;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



      final  View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        mNomE = (EditText) rootView.findViewById(R.id.input_name);
        mPrenomE =(EditText) rootView.findViewById(R.id.input_pnom);
        mDate = (EditText) rootView.findViewById(R.id.input_date);
        suiv = (Button) rootView.findViewById(R.id.btn_suiv_first);
        lang = (Spinner) rootView.findViewById(R.id.input_langue);



        mDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);
                int month = mcurrentTime.get(Calendar.MONTH);
                int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog mDatePicker;

                mDatePicker = new DatePickerDialog(rootView.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        mDate.setText(""+i2+"-"+i1+"-"+i);
                    }
                },year,month,day);





                mDatePicker = new DatePickerDialog(rootView.getContext(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay){
                        mDate.setText(""+selectedYear + "-" + (selectedMonth +1) + "-" + selectedDay);
                    }

                }, year, month,day);

                mDatePicker.setTitle(getContext().getResources().getString(R.string.day_choose));
                mDatePicker.show();

            }
        });

        lang.setAdapter(initializeSpinner(rootView.getContext()));



        suiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nom, pnom,date = mDate.getText().toString(), lg;

                nom = mNomE.getText().toString();
                pnom = mPrenomE.getText().toString();
                date = mDate.getText().toString();
                lg=lang.getSelectedItem().toString();

                if(TextUtils.isEmpty(nom)){

                    mNomE.setError("Ce champ est requis");
                    view = mNomE;
                }else {


                    FragmentManager frman = getFragmentManager();
                    FragmentTransaction ftran = frman.beginTransaction();
                    Fragment ffrag = SecondFormFragment.newInstance( nom, pnom, date, lg);
                    ftran.replace(R.id.container, ffrag)
                            .addToBackStack(null)
                            .commit();
                }



            }
        });

        suiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueRegister(v);



            }
        });

        return rootView;
    }

    public ArrayAdapter<String> initializeSpinner( Context context){

        return  new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                context.getResources().getStringArray(R.array.pref_langage_options)
        );
    }

    public void continueRegister(View v){
        String nom, pnom,date = mDate.getText().toString(), lg;
        boolean cancel = false;
        View focusView = null;
        nom = mNomE.getText().toString();
        pnom = mPrenomE.getText().toString();
        date = mDate.getText().toString();
        lg=lang.getSelectedItem().toString();

        if (TextUtils.isEmpty(nom)) {
            mNomE.setError(v.getContext().getResources().getString(R.string.error_field_required));
            v=mNomE;
            cancel=true;
        } else if(lang.getSelectedItem().toString().equals(v.getContext().getResources().getString(R.string.pref_lang_label))){

        } else {

            FragmentManager frman = getFragmentManager();
            FragmentTransaction ftran = frman.beginTransaction();
            Fragment ffrag = SecondFormFragment.newInstance(nom, pnom, date, lg);

            ftran.replace(R.id.container, ffrag)
                 .addToBackStack(null)
                 .commit();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
