package ru.lopav.kzn.aviabilets

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.aviasales.template.ui.fragment.AviasalesFragment
import ru.aviasales.core.identification.SdkConfig
import ru.aviasales.core.AviasalesSDK





class MainActivity : AppCompatActivity() {

    private var aviasalesFragment: AviasalesFragment? = null

    companion object {
        private const val TRAVEL_PAYOUTS_MARKER = "your_travel_payouts_marker"
        private const val TRAVEL_PAYOUTS_TOKEN = "your_travel_payouts_token"
        private const val SDK_HOST = "www.travel-api.pw"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AviasalesSDK.getInstance().init(this, SdkConfig(TRAVEL_PAYOUTS_MARKER, TRAVEL_PAYOUTS_TOKEN, SDK_HOST))
        setContentView(R.layout.activity_main)
        init();
    }

    private fun init() {
        initFragment();
    }

    private fun initFragment() {
        val fm = supportFragmentManager
        aviasalesFragment = fm.findFragmentByTag(AviasalesFragment.TAG) as AviasalesFragment

        val fragmentTransaction = fm.beginTransaction()
        val fragment: AviasalesFragment = aviasalesFragment ?: AviasalesFragment.newInstance() as AviasalesFragment
        fragmentTransaction.replace(R.id.fragment_place, fragment, AviasalesFragment.TAG);
        fragmentTransaction.commit();
    }

    override fun onBackPressed() {
        val fragment = aviasalesFragment ?: return
        if (!fragment.onBackPressed()) {
            super.onBackPressed()
        }
    }

    @Override
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}
