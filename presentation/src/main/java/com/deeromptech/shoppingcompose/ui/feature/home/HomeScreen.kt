package com.deeromptech.shoppingcompose.ui.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.deeromptech.domain.model.Product
import com.deeromptech.shoppingcompose.R
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: HomeViewModel = koinViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()

    var loading by remember {
        mutableStateOf(false)
    }
    var error by remember {
        mutableStateOf<String?>(null)
    }
    var feature by remember {
        mutableStateOf<List<Product>>(emptyList())

    }
    var popular by remember {
        mutableStateOf<List<Product>>(emptyList())
    }
    var categories by remember {
        mutableStateOf<List<String>>(emptyList())
    }

    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (uiState.value) {
                is HomeScreenUIEvents.Loading -> {
                    loading = true
                    error = null
                }

                is HomeScreenUIEvents.Success -> {
                    val data = (uiState.value as HomeScreenUIEvents.Success)
                    feature = data.featured
                    popular = data.popularProducts
                    categories = data.categories
                    loading = false
                    error = null
                }

                is HomeScreenUIEvents.Error -> {
                    val errorMsg = (uiState.value as HomeScreenUIEvents.Error).message
                    loading = false
                    error = errorMsg
                }
            }
            HomeContent(animatedVisibilityScope,
                feature,
                popular,
                categories,
                loading,
                error,
                onItemClick = {
//                    navController.navigate(ProductDetails(it))
                },
                onCategoryClicked = {})
        }
    }
}

@Composable
fun ProfileHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = "Hello,", style = MaterialTheme.typography.bodySmall)
                Text(
                    text = "John Doe",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.CenterEnd)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = 0.3f))
                .padding(8.dp),
            contentScale = ContentScale.Inside
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun ProfileHeaderPreview() {
    ProfileHeader()
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProductItem(
    modifier: Modifier,
    product: Product,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (Product) -> Unit
) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .size(width = 126.dp, height = 144.dp)
            .clickable {
                onItemClick(product)
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .background(Color.White)
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/${product.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 300)
                        }
                    ),
                contentScale = ContentScale.Inside
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = product.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp).sharedElement(
                    state = rememberSharedContentState(key = "title/${product.id}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = 300)
                    }
                ),
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp).sharedElement(
                    state = rememberSharedContentState(key = "price/${product.id}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = 300)
                    }
                ),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

//@Preview(showBackground = true, device = "id:pixel_5")
//@Composable
//fun ProductItemPreview() {
//    ProductItem(product = productSample)
//}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeProductRow(
    products: List<Product>,
    title: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (Product) -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(
                    Alignment.CenterStart
                ),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "View all",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(
                    Alignment.CenterEnd
                )
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow() {
            items(products, key = { it }) { product ->
                var isVisible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    isVisible = true
                }
                AnimatedVisibility(
                    visible = isVisible, enter = fadeIn() + expandVertically()
                ) {
                    ProductItem(
                        Modifier.animateItem(),
                        animatedVisibilityScope = animatedVisibilityScope,
                        product = product,
                        onItemClick = onItemClick
                    )
                }
            }
        }
    }
}



//@Preview(showBackground = true, device = "id:pixel_5")
//@Composable
//fun HomeProductRowPreview() {
//    HomeProductRow(products = emptyList(), title = "Featured")
//}

@Composable
fun SearchBar(value: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onTextChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(32.dp),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
            unfocusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
        ),
        placeholder = { Text(text = "Search", style = MaterialTheme.typography.bodySmall) },
    )
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun SearchBarPreview() {
    SearchBar(value = "", onTextChanged = {})
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeContent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    featured: List<Product>,
    popularProducts: List<Product>,
    categories: List<String>,
    isLoading: Boolean = false,
    errorMsg: String? = null,
    onItemClick: (Product) -> Unit,
    onCategoryClicked: (String) -> Unit
) {
    Column {

        ProfileHeader()
        Spacer(modifier = Modifier.size(16.dp))
        SearchBar(value = "", onTextChanged = {})
        Spacer(modifier = Modifier.size(16.dp))

        if (isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
                Text(text = "Loading...", style = MaterialTheme.typography.bodyMedium)
            }
        } else if (errorMsg != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = errorMsg, style = MaterialTheme.typography.bodyMedium)
            }
        } else {
            if (categories.isNotEmpty()) {
                LazyRow {
                    items(categories, key = { it }) { category ->
                        var isVisible by remember { mutableStateOf(false) }
                        LaunchedEffect(Unit) {
                            isVisible = true
                        }

                        AnimatedVisibility(
                            visible = isVisible, enter = fadeIn() + expandHorizontally()
                        ) {
                            Text(text = category.replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { onCategoryClicked(category) }
                                    .background(MaterialTheme.colorScheme.primary)
                                    .padding(8.dp)
                                    .animateItem())
                        }
                    }

                }
                Spacer(modifier = Modifier.size(16.dp))
            }
            if (featured.isNotEmpty()) {
                HomeProductRow(
                    products = featured,
                    animatedVisibilityScope = animatedVisibilityScope,
                    title = "Featured",
                    onItemClick = onItemClick
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
            if (popularProducts.isNotEmpty()) {
                HomeProductRow(
                    products = popularProducts,
                    animatedVisibilityScope = animatedVisibilityScope,
                    title = "Popular Products",
                    onItemClick = onItemClick
                )
            }
        }
    }
}

val productSample = Product(
    id = 1,
    title = "Product 1",
    price = 10.0,
    description = "Description 1",
    category = "Category 1",
    image = R.drawable.ic_profile.toString()
)