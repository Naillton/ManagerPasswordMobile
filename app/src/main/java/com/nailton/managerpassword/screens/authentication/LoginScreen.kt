package com.nailton.managerpassword.screens.authentication

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import com.nailton.managerpassword.data.HeaderStore
import com.nailton.managerpassword.presentation.configmodel.MyViewModel
import com.nailton.managerpassword.presentation.configmodel.ViewModelFactory
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.Injector
import com.nailton.managerpassword.routes.NavigationRoutes
import com.nailton.managerpassword.routes.graph.RootNavGraph
import com.nailton.managerpassword.ui.theme.firstColor
import com.nailton.managerpassword.ui.theme.secondColor
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.properties.Delegates

class LoginScreen {

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var mpViewModel: MyViewModel

    @Composable
    fun LoginConfig(navController: NavController) {
        val context = LocalContext.current
        val viewModelStore: ViewModelStoreOwner? = LocalViewModelStoreOwner.current
        (context.applicationContext as Injector).createMPSubComponent().inject(this)
        mpViewModel = ViewModelProvider(viewModelStore!!, factory)[MyViewModel::class.java]
        Login(navController)
    }

    @Composable
    fun TextField(
        text: String,
        onChange: (String) -> Unit,
        label: String,
        placeholder: String,
        keyboardType: KeyboardType,
        icons: ImageVector,
        visualTransformation: VisualTransformation
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onChange,
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            maxLines = 1,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            leadingIcon = {
                Icon(icons, contentDescription = "Icon")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Cyan,
                unfocusedBorderColor = Color.Black,
                focusedLeadingIconColor = Color.Cyan,
                unfocusedLeadingIconColor = Color.Black,
                focusedLabelColor = Color.Cyan,
                unfocusedLabelColor = Color.Black,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
    }

    @Composable
    fun BtnField(
        onTouch: () -> Unit,
        text: String,
        color: Color,
        isEnable: Boolean
    ) {
        Button(
            onClick = { onTouch() },
            colors = ButtonDefaults.buttonColors(color),
            enabled = isEnable
        ) {
            Text(text = text.uppercase(), color = Color.White)
        }

    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun Login(navController: NavController) {

        val context = LocalContext.current
        val owner = LocalLifecycleOwner.current
        val coroutineScope = rememberCoroutineScope()
        var txtFieldValue by rememberSaveable { mutableStateOf("") }
        var txtPassValue by rememberSaveable { mutableStateOf("") }

        fun login(email: String, pass: String, owner: LifecycleOwner): CompletableDeferred<Boolean> {
            val response = mpViewModel.loginUser(email, pass)
            val deferred = CompletableDeferred<Boolean>()
            response.observe(owner) {
                deferred.complete(it)
            }
            return deferred
        }

        val onChangeValue = { it: String ->
            txtFieldValue = it
        }

        val onChangePassValue = { it: String ->
            txtPassValue = it
        }

        fun changeValueButton(): Boolean {
            return txtFieldValue.length >= 11 && txtPassValue.length >= 8
        }

        val onTouchLogin = {
            var valueLOGIN: Boolean
            coroutineScope.launch {
                valueLOGIN = withContext(Dispatchers.Main) {
                    login(txtFieldValue, txtPassValue, owner).await()
                }
                Log.i("TAGI", valueLOGIN.toString())
                if (valueLOGIN) {
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
                }
            }
        }

        val onTouchRegister = {
            navController.navigate(NavigationRoutes.Register.routes) {
                launchSingleTop = true
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.linearGradient(colors = listOf(secondColor, firstColor))),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                text = txtFieldValue,
                onChange = onChangeValue,
                label = "E-mail",
                placeholder = "Digite seu Email",
                icons = Icons.Outlined.Email,
                keyboardType = KeyboardType.Email,
                visualTransformation = VisualTransformation.None,
            )

            TextField(
                text = txtPassValue,
                onChange = onChangePassValue,
                label = "Password",
                placeholder = "Digite sua Senha",
                icons = Icons.Outlined.Lock,
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
            )

            BtnField({ onTouchLogin() }, "Login", Color(0.475f, 0.094f, 0.82f), changeValueButton())

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Ainda não tem login ?".uppercase(),
                modifier = Modifier
                    .clickable { onTouchRegister() },
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}