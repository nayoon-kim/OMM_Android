package com.example.ohmymoney;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

public class MainActivity extends AppCompatActivity {
    private Button btnScheduling, btnBudget;
    private Button btn_kakao_login_out;
    private Button btn_google_login_out;
    private GoogleSignInClient mGoogleSignInClient;

    // 하단 네비게이션 바
    private BottomNavigationView mBottomNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log.e("getKeyHash", ""+getKeyHash(MainActivity.this));
        this.getViewObject();
        this.initListener();
    }

    public static String getKeyHash(final Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            if (packageInfo == null)
                return null;

            for (Signature signature : packageInfo.signatures) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    return android.util.Base64.encodeToString(md.digest(), android.util.Base64.NO_WRAP);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getViewObject() {
        btnScheduling = (Button) findViewById(R.id.scheduling);
        btnBudget = (Button) findViewById(R.id.budgeting);
    }

    public void initListener() {
        btnScheduling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlanActivity1.class);
                startActivity(intent);
            }
        });

        btnBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BudgetActivity.class);
                startActivity(intent);
            }
        });
    }


/*
        mBottomNV = findViewById(R.id.nav_view);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){ //NavigationItemSelected
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                BottomNavigate(menuItem.getItemId());
                return true;
            }
        });

        mBottomNV.setSelectedItemId(R.id.navigation_2);*/
        /*
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btn_kakao_login_out = (Button)findViewById(R.id.btn_kakao_login_out);
        btn_google_login_out = (Button)findViewById(R.id.btn_google_login_out);

        btn_kakao_login_out.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                UserManagement.getInstance()
                        .requestUnlink(new UnLinkResponseCallback() {
                            @Override
                            public void onSessionClosed(ErrorResult errorResult) {
                                Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                            }

                            @Override
                            public void onFailure(ErrorResult errorResult) {
                                Log.e("KAKAO_API", "연결 끊기 실패: " + errorResult);

                            }
                            @Override
                            public void onSuccess(Long result) {
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                Log.i("KAKAO_API", "연결 끊기 성공. id: " + result);
                                startActivity(intent);
                            }
                        });

            }
        });
        btn_google_login_out.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //On Succesfull signout we navigate the user back to LoginActivity
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
        });*/

/*
    private void BottomNavigate(int id){ //BottomNavigation 페이지 변경
        String tag = String.valueOf(id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if(currentFragment != null){
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if(fragment == null){
            if(id == R.id.navigation_1){
                fragment = new FragmentPage1();
            }else if(id == R.id.navigation_2){
                fragment = new FragmentPage2();
            }else{
                fragment = new FragmentPage3();
            }

            fragmentTransaction.add(R.id.content_layout, fragment, tag);
        }else{
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
    }*/

    // 아래 방식으로 했을 때 작동 왜 안하는지 알아내기!!!
    /*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_google_login_out:
                GoogleLogout();
                break;
        }
>>>>>>> Stashed changes
    }

    private void GoogleLogout() {

        mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //On Succesfull signout we navigate the user back to LoginActivity
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void KakaoLogout(){
        UserManagement.getInstance()
                .requestUnlink(new UnLinkResponseCallback() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                    }

                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "연결 끊기 실패: " + errorResult);

                    }
                    @Override
                    public void onSuccess(Long result) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        Log.i("KAKAO_API", "연결 끊기 성공. id: " + result);
                        startActivity(intent);
                    }
                });
    }*/

}
