package toru.io.my.viperusage.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import toru.io.my.viperusage.R;
import toru.io.my.viperusage.databinding.ActivityInitBinding;
import toru.io.my.viperusage.model.InstagramItemModel;
import toru.io.my.viperusage.network.services.RequestModel;
import toru.io.my.viperusage.presenter.InitPresenter;
import toru.io.my.viperusage.presenter.InitPresenterImp;
import toru.io.my.viperusage.view.ui.InitViewAdapter;

public class InitActivity extends AppCompatActivity implements InitView {
    private static final String TAG = InitActivity.class.getSimpleName();

    private InitPresenter presenter;

    private ActivityInitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_init);
        presenter = new InitPresenterImp(this);
        presenter.onCreate();

        binding.initRecyclerview.setAdapter(new InitViewAdapter(new ArrayList<>()));

        RequestModel model = new RequestModel();
        model.id = "toru_0239";
        model.maxId = "0";
        presenter.onUpdateStart(model);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        presenter = null;
        super.onDestroy();
    }

    @Override
    public void onUpdate(List<InstagramItemModel> result) {
        Log.w(TAG, "onUpdate, list size : " + result.size());
        binding.initRecyclerview.setAdapter(new InitViewAdapter(result));
        binding.initRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }
}