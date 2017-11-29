package ru.navigator.mallnavigator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.navigator.mallnavigator.network.NetworkCallback;

public class MainActivity extends AppCompatActivity implements NetworkCallback<String> {

    @BindView(R.id.mallAutoTV)
    AutoCompleteTextView mallTV;

    @BindView(R.id.formShopAutoTv)
    AutoCompleteTextView fromShopTV;

    @BindView(R.id.toShopAutoTv)
    AutoCompleteTextView toShopTV;

    private List<String> mallNames = new ArrayList<>();

    private List<String> shopNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getNames();

        ArrayAdapter<String> mallAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, mallNames);
        mallTV.setAdapter(mallAdapter);

        ArrayAdapter<String> shopAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, shopNames);
        fromShopTV.setAdapter(shopAdapter);
        toShopTV.setAdapter(shopAdapter);
    }

    @OnClick(R.id.settingsBtn)
    public void openSettings() {

    }

    @OnClick(R.id.getPathBtn)
    public void getPath() {

    }

    private void getNames() {
        mallNames.add("Aviapark");
        mallNames.add("Evropeiskiy");
        mallNames.add("Zolotoy Vavilon");

        shopNames.add("Nike");
        shopNames.add("Adidas");
        shopNames.add("H&M");
        shopNames.add("Zara");
        shopNames.add("Gap");
    }

    @Override
    public void update(String result) {

    }
}
