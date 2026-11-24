package com.example.practica3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddLink
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.practica3.ui.theme.Practica3Theme
import com.example.practica3.ui.theme.Practica3Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Contacts

class MainActivity : ComponentActivity() {

    private val TAB = "MainActivity"
    private var keepSplash = true
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen=installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplash }
        lifecycleScope.launch {
            delay(3000)
            keepSplash = false
        }
        super.onCreate(savedInstanceState)
        ShowToast("Funcion onCreate()")
        enableEdgeToEdge()
        setContent {
            Practica3Theme {
                ViewContainer()
            }
        }
    }
    override fun onStart(){
        super.onStart()
        ShowToast("Funcion OnStart()")
    }
    fun ShowToast(message:String){
        val toast = Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        )
        toast.show()
        Log.i(TAB,message)
    }
}


fun openWebScreen(context: Context) {
    try {
        val url = Uri.parse("https://github.com/")
        val intent = Intent(Intent.ACTION_VIEW, url)
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Escoge un navegador", Toast.LENGTH_LONG).show()
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    device = "spec:width=411dp,height=891dp,orientation=portrait",
    locale = "es-rMX",
    showBackground = true,
)

@Composable
fun ViewContainer(){
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val snackbarMessage = stringResource(R.string.texto_snackbar)

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },

        topBar = {
            TopAppBar(
                title = {Text(stringResource(R.string.inicio))},
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    scrolledContainerColor = MaterialTheme.colorScheme.secondary,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    subtitleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_btn_desc),
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { openWebScreen(context) }) {
                        Icon(
                            imageVector = Icons.Default.AddLink,
                            contentDescription = "Marcar como favorito",
                            tint = Color.White
                        )

                    }

                    IconButton(
                        onClick = {
                            val intent = Intent(context, SecondActivity::class.java)
                            context.startActivity(intent)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Compartir",
                            tint = Color.White
                        )
                    }
                    DropDownMenu()
                }

            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.home_fab_text),
                    color = Color.White,
                    fontSize = 18.sp,
                )
            }
        },

        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Mostrar")},
                icon = { Icon(Icons.Default.Add, contentDescription = "") },
                onClick={
                    scope.launch {
                        val result = snackbarHostState
                            .showSnackbar(
                                message = snackbarMessage,
                                actionLabel = "Action",
                                duration = SnackbarDuration.Indefinite
                            )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {

                            }
                            SnackbarResult.Dismissed -> {

                            }
                        }
                    }
                }
            )
        }


        //modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        ContentView(modifier = Modifier.padding(innerPadding))
    }
}
@Composable
fun ContentView(modifier: Modifier = Modifier) {
    //var counter by remember { mutableStateOf(0) }
    var counter by rememberSaveable { mutableStateOf(0) }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
    )
    {
        item {
            Image(
                painter = painterResource(id = R.drawable.samplephoto),
                contentDescription = "Ilustración de Android",
                modifier = Modifier.fillMaxWidth().height(400.dp)
            )
            Row(modifier = Modifier.padding(top=2.dp)){
                Image(
                    painter = painterResource(id= R.drawable.outline_favorite_24),
                    contentDescription = "Star",
                    modifier = Modifier.clickable{ counter++ }
                )
                Text(
                    text = counter.toString(),
                    fontSize = 20.sp,
                    color= Color.DarkGray,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Text(
                text = stringResource(R.string.materia),
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center

            )
            Text(
                text = stringResource(R.string.nombre),
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.apellido),
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center

            )
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
            ) {
                item {
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.webicon1),
                        contentDescription = "mobileApps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.webicon2),
                        contentDescription = "mobileApps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.webicon3),
                        contentDescription = "mobileApps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.webicon4),
                        contentDescription = "mobileApps"
                    )
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.webicon5),
                        contentDescription = "mobileApps"
                    )
                }
            }
        }
    }
}


@Composable
fun DropDownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.options_menu),
                tint = Color.White
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            // LLAMADA
            DropdownMenuItem(
                text = { Text("Llamada") },
                onClick = {
                    expanded = false
                    val intent = Intent(Intent.ACTION_DIAL)
                    context.startActivity(intent)
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Call,
                        contentDescription = "Llamada"
                    )
                }
            )

            // BLUETOOTH
            DropdownMenuItem(
                text = { Text("Bluetooth") },
                onClick = {
                    expanded = false
                    val intent = Intent(
                        android.provider.Settings.ACTION_BLUETOOTH_SETTINGS
                    )
                    context.startActivity(intent)
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Bluetooth,
                        contentDescription = "Bluetooth"
                    )
                }
            )

            Divider()

            // CONTACTOS
            DropdownMenuItem(
                text = { Text("Contactos") },
                onClick = {
                    expanded = false
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        android.provider.ContactsContract.Contacts.CONTENT_URI
                    )
                    context.startActivity(intent)
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Contacts,
                        contentDescription = "Contactos"
                    )
                }
            )
        }
    }
}
