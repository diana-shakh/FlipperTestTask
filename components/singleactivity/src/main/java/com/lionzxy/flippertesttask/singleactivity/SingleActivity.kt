package com.lionzxy.flippertesttask.singleactivity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.content.res.ResourcesCompat
import com.arkivanov.decompose.defaultComponentContext
import com.lionzxy.flippertesttask.core.di.ComponentHolder
import com.lionzxy.flippertesttask.core.log.LogTagProvider
import com.lionzxy.flippertesttask.core.log.info
import com.lionzxy.flippertesttask.main.BackgroundColor
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
            componentContext = defaultComponentContext(),
            onBackgroundColorChanged = { backgroundColor ->
                when (backgroundColor) {
                    BackgroundColor.BLUE -> {
                        window.statusBarColor =
                            ResourcesCompat.getColor(resources, R.color.blue, theme)
                        window.navigationBarColor =
                            ResourcesCompat.getColor(resources, R.color.blue, theme)
                        Log.e("color","blue")
                    }

                    BackgroundColor.WHITE -> {
                        window.statusBarColor =
                            ResourcesCompat.getColor(resources, R.color.navigationBar, theme)
                        window.navigationBarColor =
                            ResourcesCompat.getColor(resources, R.color.accent, theme)
                        Log.e("color","white")
                    }
                }
            }

        )

        setContent {
            root.Render(
                modifier = Modifier
                    .fillMaxSize()
            )

        }
        info { "Create new activity with hashcode: ${this.hashCode()} " + "and intent $intent" }
    }
}