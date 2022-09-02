/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.shapes
import com.example.androiddevchallenge.utils.EdgeToEdgeUtils.setEdgeWithLightTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEdgeWithLightTheme(/*isSystemInDarkTheme()*/true)
        setContent {
            MyTheme {
                HomePage()
            }
        }
    }

    data class ImageItem(val name: String, @DrawableRes val drawableId: Int)

    private val plantList = listOf(
        ImageItem("Desert chic", R.drawable.desert_chic),
        ImageItem("Tiny terrariums", R.drawable.tiny_terrariums),
        ImageItem("Jungle Vibes", R.drawable.jungle_vibes),
        ImageItem("Monstera", R.drawable.monstera),
        ImageItem("Aglaonema", R.drawable.aglaonema),
        ImageItem("Peace lily", R.drawable.peace_lily),
        ImageItem("Fiddle leaf tree", R.drawable.fiddle_leaf),
    )

    private val designList = listOf(
        ImageItem("Monstera", R.drawable.monstera),
        ImageItem("Aglaonema", R.drawable.aglaonema),
        ImageItem("Peace lily", R.drawable.peace_lily),
        ImageItem("Fiddle leaf tree", R.drawable.fiddle_leaf),
        ImageItem("Desert chic", R.drawable.desert_chic),
        ImageItem("Tiny terrariums", R.drawable.tiny_terrariums),
        ImageItem("Jungle Vibes", R.drawable.jungle_vibes)
    )

    private val navList = listOf(
        ImageItem("Home", R.drawable.ic_home),
        ImageItem("Favorites", R.drawable.ic_favorite_border),
        ImageItem("Profile", R.drawable.ic_account_circle),
        ImageItem("Cart", R.drawable.ic_shopping_cart)
    )

    @Composable
    fun HomePage() {
        Scaffold(bottomBar = {
            BottomBar()
        }) {
            HomeContent(it)
        }
    }

    @Composable
    fun SearchBar() {
        TextField(value = "",
            onValueChange = {},
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 40.dp).fillMaxWidth()
                .height(56.dp)
                .border(1.dp, MaterialTheme.colors.onPrimary, shapes.small),
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            leadingIcon = {
                Icon(painter = rememberVectorPainter(image = ImageVector.vectorResource(R.drawable.ic_search)),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp))
            },
            label = {
                Text("Search",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground)
            }
        )
    }

    /*底部BottomNavigationBar的实现 较为简单*/
    @Composable
    fun BottomBar() {
        // Stateful
        val select = remember { mutableStateOf(0) }
        BottomNavigation(modifier = Modifier.fillMaxWidth().height(56.dp)
            .background(MaterialTheme.colors.primary)) {
            navList.forEachIndexed { index, item ->
                BottomNavigationItem(index == select.value,
                    onClick = { select.value = index },
                    icon = {
                        Icon(
                            painter = painterResource(item.drawableId),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                    }, label = {
                        Text(text = navList[index].name,
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onPrimary)
                    })
            }
        }
    }


    @Composable
    fun BrowserContent() {
        Column(modifier = Modifier.fillMaxWidth().padding(start = 16.dp).wrapContentSize()) {
            Text(text = "Browse themes",
                modifier = Modifier.paddingFromBaseline(top = 32.dp, bottom = 16.dp),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground)

            LazyRow {
                items(plantList.size) {
                    if (it != 0) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    PlantCard(plantList[it])
                }

            }
        }
    }

    @Composable
    fun PlantCard(item: ImageItem = plantList[0]) {
        Card(modifier = Modifier.size(136.dp), shape = MaterialTheme.shapes.small,
            backgroundColor = MaterialTheme.colors.background) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(item.drawableId),
                    contentScale = ContentScale.Crop,
                    contentDescription = null, modifier = Modifier.height(96.dp).fillMaxWidth())
                Box(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
                    Text(text = item.name,
                        modifier = Modifier.align(Alignment.CenterStart)
                            .padding(bottom = 12.dp, start = 12.dp),
                        style = MaterialTheme.typography.h2)
                }
            }
        }


    }


    @Composable
    fun HomeContent(paddingValues: PaddingValues = PaddingValues(0.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            SearchBar()
            BrowserContent()
            DesignGardenContent()
        }
    }

    @Composable
    fun DesignGardenContent() {
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            // The title
            // using space between to place two item at start and end
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Design your home garden",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                        .wrapContentWidth())

                Image(painter = rememberVectorPainter(ImageVector.vectorResource(R.drawable.ic_filter_list)),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 12.dp).size(24.dp)
                        .align(Alignment.CenterVertically))
            }
            // The list content
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(designList.size) { index ->
                    if (index != 0) {
                        Spacer(Modifier.height(8.dp))
                    }
                    DesignItemContent(designList[index])
                }

            }

        }
    }

    @Composable
    fun DesignItemContent(item: ImageItem = designList[0]) {
        Box(modifier = Modifier.fillMaxWidth().height(64.dp)) {
            Row(modifier = Modifier.wrapContentWidth().fillMaxHeight().padding(start = 16.dp)) {
                Image(painter = painterResource(item.drawableId),
                    contentDescription = item.name,
                    modifier = Modifier.size(64.dp).clip(MaterialTheme.shapes.small),
                    contentScale = ContentScale.Crop)
                Column(modifier = Modifier.padding(start = 16.dp).fillMaxHeight()
                    .wrapContentWidth()) {
                    Text(text = item.name,
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.paddingFromBaseline(top = 24.dp))

                    Text(text = "This is a description",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.paddingFromBaseline(top = 16.dp))
                }
            }
            val checkStatus = remember { mutableStateOf(false) }
            Checkbox(checked = checkStatus.value,
                onCheckedChange = { checkStatus.value = !checkStatus.value },
                modifier = Modifier.align(Alignment.CenterEnd))

            Divider(modifier = Modifier.align(Alignment.BottomEnd).padding(start = 88.dp)
                .fillMaxWidth(),
                thickness = (0.5).dp,
                color = MaterialTheme.colors.onBackground)
        }

    }


    @Preview(uiMode = UI_MODE_NIGHT_NO)
    @Composable
    fun MockWelcomePageLight() {
        MyTheme(darkTheme = false) {
            HomePage()
        }
    }


    @Preview(uiMode = UI_MODE_NIGHT_YES)
    @Composable
    fun MockWelcomePageDark() {
        MyTheme(darkTheme = true) {
            HomePage()
        }
    }
}
