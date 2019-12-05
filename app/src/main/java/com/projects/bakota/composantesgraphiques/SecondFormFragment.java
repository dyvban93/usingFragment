package com.projects.bakota.composantesgraphiques;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFormFragment extends Fragment {
    private static final String ARG_NOM = "nom";
    private static final String ARG_PNOM = "pnom";
    private static final String ARG_DATE= "daten";
    private static final String ARG_LANG = "lang";


    // TODO: Rename and change types of parameters
    private String mNom;
    private String mPnom;
    private String mDate;
    private String mLang;

    EditText mPhone, mEmail,mAdresse;
    Button mSuiv;

    Context context;
    private OnFragmentInteractionListener mListener;

    public SecondFormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param nom Parameter 1.
     * @param pnom Parameter 2.
     * @return A new instance of fragment SecondFormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFormFragment newInstance(String nom, String pnom, String date, String lang) {
        SecondFormFragment fragment = new SecondFormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOM, nom);
        args.putString(ARG_PNOM, pnom);
        args.putString(ARG_DATE, date);
        args.putString(ARG_LANG, lang);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNom = getArguments().getString(ARG_NOM);
            mPnom = getArguments().getString(ARG_PNOM);
            mDate = getArguments().getString(ARG_DATE);
            mLang = getArguments().getString(ARG_LANG);


            context = getContext();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_second_form, container, false);

        mEmail = (EditText) rootView.findViewById(R.id.input_email);
        mPhone = (EditText) rootView.findViewById(R.id.input_contact);
        mAdresse = (EditText) rootView.findViewById(R.id.input_adresse);
        mSuiv = (Button) rootView.findViewById(R.id.btn_second_suiv);

        mSuiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueRegister2();



                final String contact, mail,adresse;
                boolean cancel = false;
                View focusView = null;

                contact = mPhone.getText().toString();
                mail = mEmail.getText().toString();
                adresse = mAdresse.getText().toString();

                Intent intent = new Intent(context,Main2Activity.class);

                intent.putExtra("nom",mNom);

                startActivity(intent);

            }
        });

        return rootView;
    }


    public void continueRegister2(){

        final String contact, mail,adresse;
        boolean cancel = false;
        View focusView = null;

        contact = mPhone.getText().toString();
        mail = mEmail.getText().toString();
       adresse = mAdresse.getText().toString();

        if (TextUtils.isEmpty(contact)) {
            mPhone.setError(context.getResources().getString(R.string.error_field_required));
            focusView=mPhone;
            cancel=true;
            return;
        }


        if( ! TextUtils.isEmpty(mail) && !Patterns.EMAIL_ADDRESS.matcher(mail).matches() ){
            mEmail.setError(context.getResources().getString(R.string.error_field_required));
            focusView=mEmail;
            cancel=true;
            return;
        }

        //verification du numéro
        if(contact.length() == 9){
            //on vérifie les premiers chiffres
            if(contact.charAt(1) == '9' || contact.charAt(1) == '7' || contact.charAt(1) == '6' || contact.charAt(1) == '5' || contact.charAt(1) == '8' || contact.charAt(1) == '2'){

            } else {
                mPhone.setError(context.getResources().getString(R.string.error_field_required));
                focusView=mPhone;
                cancel=true;
                return;
            }
        } else {
            mPhone.setError(context.getResources().getString(R.string.error_field_required));
            focusView=mPhone;
            cancel=true;
            return;
        }

        Intent intent = new Intent(context,Main2Activity.class);
        //ajouter les données
        startActivity(intent);
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
