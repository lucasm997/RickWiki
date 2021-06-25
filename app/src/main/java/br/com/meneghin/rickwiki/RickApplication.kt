package br.com.meneghin.rickwiki

import android.app.Application
import br.com.meneghin.rickwiki.injection.dataModules
import br.com.meneghin.rickwiki.injection.domainModules
import br.com.meneghin.rickwiki.injection.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RickApplication)

            modules(dataModules + domainModules + presentationModules)
        }
    }
}
