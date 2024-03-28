package org.d3if3157.assessment1.ui.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.d3if3157.assessment1.Navigation.Screen
import org.d3if3157.assessment1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ), actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ) {
                        Icon(imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(id = R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ){
            padding -> ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier : Modifier){
    var nilaiIndo by rememberSaveable { mutableStateOf("") }
    var nilaiIndoError by rememberSaveable { mutableStateOf(false) }

    var nilaiMtk by rememberSaveable { mutableStateOf("") }
    var nilaiMtkError by rememberSaveable { mutableStateOf(false) }

    var nilaiIpa by rememberSaveable { mutableStateOf("") }
    var nilaiIpaError by rememberSaveable { mutableStateOf(false) }

    var nilaiInggris by rememberSaveable { mutableStateOf("") }
    var nilaiInggrisError by rememberSaveable { mutableStateOf(false) }

    var nilaiAkhir by rememberSaveable { mutableFloatStateOf(0f) }
    var kategori by rememberSaveable { mutableStateOf(0) }

    val radioOptions = listOf(
        stringResource(id = R.string.sma),
        stringResource(id = R.string.smp)
    )
    var tahun by rememberSaveable { mutableStateOf(radioOptions[0]) }

    var kondisi by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Nilai Matematika
        OutlinedTextField(
            value = nilaiMtk, onValueChange = { nilaiMtk = it },
            label = { Text(text = stringResource(id = R.string.matematika))},
            isError = nilaiMtkError,
            trailingIcon = { IconPicker(nilaiMtkError)},
            supportingText = { ErrorHint(nilaiMtkError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        //Nilai Ipa
        OutlinedTextField(
            value = nilaiIpa, onValueChange = { nilaiIpa = it },
            label = { Text(text = stringResource(id = R.string.ipa))},
            isError = nilaiIpaError,
            trailingIcon = { IconPicker(nilaiIpaError)},
            supportingText = { ErrorHint(nilaiIpaError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        //Nilai Indonesia
        OutlinedTextField(
            value = nilaiIndo, onValueChange = { nilaiIndo = it },
            label = { Text(text = stringResource(id = R.string.b_indo))},
            isError = nilaiIndoError,
            trailingIcon = { IconPicker(nilaiIndoError)},
            supportingText = { ErrorHint(nilaiIndoError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        if (tahun == radioOptions[0]){
        //Nilai Inggris
        OutlinedTextField(
            value = nilaiInggris, onValueChange = { nilaiInggris = it },
            label = { Text(text = stringResource(id = R.string.b_inggris))},
            isError = nilaiInggrisError,
            trailingIcon = { IconPicker(nilaiInggrisError)},
            supportingText = { ErrorHint(nilaiInggrisError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        }
        Row {
            radioOptions.forEach{ text ->
                tahunOpsi(
                    label = text,
                    isSelected = tahun == text,
                    modifier = Modifier
                        .selectable(
                            selected = tahun == text,
                            onClick = { tahun = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        Button(
            onClick = {
                nilaiMtkError = (nilaiMtk == "")
                nilaiIpaError = (nilaiIpa == "")
                nilaiIndoError = (nilaiIndo == "")
                if (tahun == radioOptions[0]){
                nilaiInggrisError = (nilaiInggris == "")
                }else{
                    nilaiInggrisError = false
                    nilaiInggris = "0"
                }
                if (nilaiIndoError || nilaiInggrisError || nilaiMtkError || nilaiIpaError)return@Button

                kondisi = true
                nilaiAkhir = hitungHasil(nilaiIndo.toFloat(), nilaiInggris.toFloat(),nilaiIpa.toFloat(), nilaiMtk.toFloat(), tahun == radioOptions[0])
                kategori = getKategori(nilaiAkhir)
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.hitung))
        }
        if (kondisi == true){
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(R.string.hasil, nilaiAkhir),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(kategori).uppercase(),
                style = MaterialTheme.typography.headlineLarge
            )
            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(R.string.bagikan_template,
                            nilaiMtk,nilaiIpa,nilaiIndo, nilaiInggris, nilaiAkhir,context.getString(kategori).uppercase())

                    )
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.bagikan))
            }
        }
    }
}

@Composable
fun IconPicker(isError: Boolean){
    if (isError){
        Icon(imageVector =Icons.Filled.Warning, contentDescription = null)
    } else {

    }
}
@Composable
fun ErrorHint(isErorr: Boolean){
    if (isErorr){
        Text(text = stringResource(id = R.string.input_invalid))
    }
}
@Composable
fun tahunOpsi(label: String, isSelected: Boolean, modifier: Modifier){
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
private fun hitungHasil(indo: Float, inggris: Float, ipa: Float, mtk: Float, tahun: Boolean): Float {
    var hasil = 0f
    if (tahun){
        hasil = (indo+inggris+mtk+ipa)/4
    } else {
        hasil =(indo+mtk+ipa)/3
    }
    return hasil
}
private fun getKategori(nilaiAkhir: Float): Int {
    var kategori: Int = 0
    if (nilaiAkhir > 85){
        kategori = R.string.sangat_baik
    } else if (nilaiAkhir > 70){
        kategori = R.string.baik
    }else if(nilaiAkhir > 55){
        kategori = R.string.cukup
    } else{
        kategori = R.string.kurang
    }
    return kategori
}

private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}