package com.nailton.managerpassword.screens.authenticated

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import com.nailton.managerpassword.data.passworddata.PasswordData
import com.nailton.managerpassword.presentation.configmodel.MyViewModel
import com.nailton.managerpassword.presentation.configmodel.ViewModelFactory
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.Injector
import com.nailton.managerpassword.routes.graph.RootNavGraph
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeScreen {

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var mpViewModel: MyViewModel

    @Composable
    fun HomeConfig(navController: NavController) {
        val context = LocalContext.current
        val viewModelStore: ViewModelStoreOwner? = LocalViewModelStoreOwner.current
        (context.applicationContext as Injector).createMPSubComponent().injectHome(this)
        mpViewModel = ViewModelProvider(viewModelStore!!, factory)[MyViewModel::class.java]
        Home(navController)
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun Home(navController: NavController) {

        val context = LocalContext.current
        val owner = LocalLifecycleOwner.current
        val coroutineScope = rememberCoroutineScope()

        fun getPasswords(owner: LifecycleOwner): CompletableDeferred<List<PasswordData>?> {
            val response = mpViewModel.getPasswords()
            val deferred = CompletableDeferred<List<PasswordData>?>()
            response.observe(owner) {
                deferred.complete(it)
            }
            return deferred
        }

        val onTouchPass ={
            coroutineScope.launch {
                val valuePass: List<PasswordData>? = withContext(Dispatchers.Main) {
                    getPasswords(owner).await()
                }
                Log.i("TAGI", valuePass.toString())
                /*if (valueLOGIN) {
                    Toast.makeText(
                        context,
                        "Logged",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(RootNavGraph.Graph.AUTHENTICATED) {
                        launchSingleTop = true
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Invalid Fields",
                        Toast.LENGTH_LONG
                    ).show()
                }*/
            }
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "home",
                tint = Color.Blue,
                modifier = Modifier.size(150.dp)
                    .align(Alignment.Center)
            )

            Button(
                onClick = { onTouchPass() },
                colors = ButtonDefaults.buttonColors(Color.Cyan),
            ) {
                Text(text = "text".uppercase(), color = Color.White)
            }
        }
    }
}