package com.lionzxy.flippertesttask.singleactivity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.content.res.ResourcesCompat
import com.arkivanov.decompose.defaultComponentContext
import com.lionzxy.flippertesttask.core.di.ComponentHolder
import com.lionzxy.flippertesttask.core.log.LogTagProvider
import com.lionzxy.flippertesttask.core.log.info
import com.lionzxy.flippertesttask.main.MainDecomposeComponent
import com.lionzxy.flippertesttask.singleactivity.di.SingleActivityComponent
import javax.inject.Inject

class SingleActivity : AppCompatActivity(), LogTagProvider {
    override val TAG = "SingleActivity"

    @Inject
    lateinit var mainDecomposeComponentFactory: MainDecomposeComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentHolder.component<SingleActivityComponent>().inject(this)

        val root = mainDecomposeComponentFactory(
            componentContext = defaultComponentContext()
        )

        window.statusBarColor = ResourcesCompat.getColor(resources, R.color.background, theme)
        window.navigationBarColor = ResourcesCompat.getColor(resources, R.color.accent, theme)

        setContent {
            root.Render(
                modifier = Modifier
                    .fillMaxSize()
            )

        }
        info { "Create new activity with hashcode: ${this.hashCode()} " + "and intent $intent" }
    }
}