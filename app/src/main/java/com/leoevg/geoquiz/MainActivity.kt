package com.leoevg.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.leoevg.geoquiz.navigation.MainNavigation
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object RetrofitInstance{
//    private const val BASE_URL ="https://example.com/"
//
//    fun getInstance(): Retrofit {
//        return Retrofit.Builder().baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create()
//                .build()
//    }
//}

//fun getInstance(): Retrofit {
//    val client = OkHttpClient()
//    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    val clientBuilder: OkHttpClient.Builder =
//        client.newBuilder().addInterceptor(interceptor as HttpLoggingInterceptor)
//
//    return Retrofit.Builder().baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(clientBuilder.build())
//        .build()
//}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainUI(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainUI(modifier: Modifier = Modifier) {
    MainNavigation(modifier)
}

@Preview(showBackground = true)
@Composable
fun MainUIIPreview() {
    GeoQuizTheme {
        MainUI()
    }
}