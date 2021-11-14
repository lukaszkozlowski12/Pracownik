package com.example.pracownik;

import android.content.Context;
import android.content.DialogInterface;

public class AlertDialog {






    // final String nazwapola, final String nazwanawozu, final String dawka, final String data, final String id_nawoz
    public void AlertDialogAkceptujZadanie(Context context, String zadanie) {
        // setup the alert builder
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle("Edycja danych"); // tytuł okna dialogowego
        builder.setMessage("Czy chcesz akceptować to zadanie - "+zadanie+"?");
        // dodanie i obsługa przycisku Tak
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

              /*  Intent punkty = new Intent(getApplicationContext(),ZobaczZadania.class);
                // umieszczenie w intencji danych zabiegu
                punkty.putExtra("nazwa_pola",nazwapola);
                punkty.putExtra("nazwa_nawozu",nazwanawozu);
                punkty.putExtra("dawka",dawka);
                punkty.putExtra("data_wykonania",data);
                punkty.putExtra("id_nawoz",id_nawoz);
                startActivity(punkty);
                finish();*/

            }
        });
        // dodanie przycisku anuluj
        builder.setNegativeButton("Anuluj", null);
        // create and show the alert dialog
        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }
}
