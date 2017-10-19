package com.solrom.edgar.petagram40.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.solrom.edgar.petagram40.R;
import com.solrom.edgar.petagram40.adapter.MascotaAdaptador;
import com.solrom.edgar.petagram40.adapter.PageAdapter;
import com.solrom.edgar.petagram40.fragment.PrincipalFragment;
import com.solrom.edgar.petagram40.fragment.RecyclerViewFragment;
import com.solrom.edgar.petagram40.pojo.Mascota;
import com.solrom.edgar.petagram40.restApi.ConstantesRestApi;

import java.util.ArrayList;

/*public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar     = (Toolbar)findViewById(R.id.toolbar);
        tabLayout   = (TabLayout)findViewById(R.id.tabLayout);
        viewPager   = (ViewPager) findViewById(R.id.viewPager);
        Log.e("MainActivity", "onCreate");
        setUpViewPager();

        if (toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Oculta el titulo del ToolBar
        }

        ImageView imgFavoritas = (ImageView)findViewById(R.id.imgFavoritas);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "¡Hola! este botón no sirve de nada ¡jajaja!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        //infla menu, agrega items a la action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.mContacto:
                Intent intent1 = new Intent(this, FormularioActivity.class);
                startActivity(intent1);
                break;

            case R.id.mAcercaDe:
                Intent intent2 = new Intent(this, AcercadeActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    // muestra el activity de las mascotas favoritas
    public void irFavoritas(View view){
        Intent intent = new Intent(this, MascotasFavoritas.class);
        startActivity(intent);
    }

    // Método para cargar el ArrayList con los fragments que tenemos
    private ArrayList<Fragment> agregarFragments(){
        Log.e("MainActivity", "agregarFragments");
        ArrayList<Fragment> fragments = new ArrayList<>();
        //carga los fragments en el orden deseado
        //fragments.add(new MascotasFavoritas());
        fragments.add(new HomeFragment());
        fragments.add(new PerfilFragment());
        return  fragments;
    }

    // activa los fragments
    private void setUpViewPager(){
        // inicializa el viewPager con instancia de la clase PageAdapter, se le pasa el manejador de fragments y
        // se llama a la funcion agregarFragments que devuelve el ArrayList con los fragments.
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icons8_hotel_96);
        tabLayout.getTabAt(1).setIcon(R.drawable.icons8_dog_bowl_96);
    }
}*/

public class MainActivity extends AppCompatActivity {


    public ArrayList<Mascota> mascotas;
    public RecyclerView listaMascotas;
    public MascotaAdaptador adapter;
    public Integer likesRecibidos = 0;
    public TextView tvLikes;
    public TextView tvNombreMascota;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //public ImageButton btnFavoritos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar     = (Toolbar)findViewById(R.id.toolbar);
        tabLayout   = (TabLayout) findViewById(R.id.tabLayout);
        viewPager   = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();

        if(toolbar != null)
        {
            setSupportActionBar(toolbar);
        }

        Bundle extras       = getIntent().getExtras();

        if(extras != null)
        {
            String usuario          = extras.getString("usuario");

            if(!usuario.equals(""))
            {
                Toast.makeText(this, usuario, Toast.LENGTH_SHORT).show();
                ConstantesRestApi.usuario = usuario;
            }
        }



        if(ConstantesRestApi.usuario == null)
        {
            SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
            String verifica = prefe.getString("usuario","");

            if(!verifica.equals(""))
            {
                ConstantesRestApi.usuario = verifica;
            }
        }


    }

    private ArrayList<Fragment> agregarFragments()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PrincipalFragment());

        return fragments;
    }

    private void setUpViewPager()
    {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager() , agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icons8_home_100);
        tabLayout.getTabAt(1).setIcon(R.drawable.icons8_dog_bowl_96);
    }


    private void iniciaListaMascotas()
    {
        mascotas = new ArrayList<Mascota>();
    }



    private void iniciaAdapterMascotas()
    {
        adapter = new MascotaAdaptador(mascotas,this);
        listaMascotas.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones , menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.mContacto:
                Intent intentContacto = new Intent(getApplicationContext() , FormularioActivity.class);
                startActivity(intentContacto);
                break;
            case R.id.mFavoritos:
                break;
            case R.id.mAcercaDe:
                Intent intentAcercaDe = new Intent(getApplicationContext() , AcercadeActivity.class);
                startActivity(intentAcercaDe);
                break;
            case R.id.mConfigurarCuenta:
                Intent intentConfigurarCuenta = new Intent(getApplicationContext() , ConfigurarCuentaActivity.class);
                startActivity(intentConfigurarCuenta);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
