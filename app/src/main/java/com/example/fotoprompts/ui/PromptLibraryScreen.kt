package com.example.fotoprompts.ui

import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.fotoprompts.data.PromptRepository
import com.example.fotoprompts.model.PromptCategory
import com.example.fotoprompts.model.PromptItem
import kotlinx.coroutines.launch

private const val PREFS_NAME = "foto_prompts_prefs"
private const val KEY_FAVORITES = "favorites"

private val FAVORITE_ACCENT = Color(0xFFFF4D8D)

private val CategoryAccents: Map<PromptCategory, Color> = mapOf(
    PromptCategory.COUPLES to Color(0xFFE85D9E),
    PromptCategory.INDIAN_STYLES to Color(0xFFE0A038),
    PromptCategory.PHOTOGRAPHY to Color(0xFF4A90D9),
    PromptCategory.ANIME_CARTOON to Color(0xFFEC6FA8),
    PromptCategory.ART_PAINTING to Color(0xFFB57EDC),
    PromptCategory.RETRO_VINTAGE to Color(0xFFD4A05C),
    PromptCategory.SCIFI_FUTURE to Color(0xFF2FBFAE),
    PromptCategory.FANTASY_HISTORY to Color(0xFF3E9160),
    PromptCategory.FASHION_PRODUCT to Color(0xFFE8607B),
    PromptCategory.FUN_NOVELTY to Color(0xFFD9A62E),
    PromptCategory.CAMERA_FX to Color(0xFF5C6BC0)
)

private fun PromptCategory.accent(): Color = CategoryAccents[this] ?: Color(0xFF7C4DFF)

/** Short form for compact spaces (the avatar tray) — full label still shows in the sheet and on cards. */
private fun PromptCategory.shortLabel(): String = label.substringBefore(" & ")

/** A bold two-tone gradient derived from a single accent color, used for poster cards and tiles. */
private fun posterGradient(accent: Color): Brush =
    Brush.linearGradient(listOf(accent, lerp(accent, Color.Black, 0.55f)))

/** Darkened accent for an icon sitting on a white/light circle — guarantees contrast for light accents (gold, tan). */
private fun iconTintOnWhite(accent: Color): Color = lerp(accent, Color.Black, 0.3f)

/** Black or white, whichever reads better on top of the given background color. */
private fun contentColorFor(background: Color): Color =
    if (background.luminance() > 0.5f) Color.Black else Color.White

private fun loadFavorites(context: Context): Set<String> =
    context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        .getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()

private fun saveFavorites(context: Context, favorites: Set<String>) {
    context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        .edit()
        .putStringSet(KEY_FAVORITES, favorites)
        .apply()
}

private fun sharePrompt(context: Context, prompt: PromptItem) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, prompt.prompt)
    }
    context.startActivity(Intent.createChooser(intent, "Share prompt via"))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromptLibraryApp() {
    val context = LocalContext.current
    val clipboard = LocalClipboardManager.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val searchFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    var selectedTab by rememberSaveable { mutableStateOf(0) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var searchActive by rememberSaveable { mutableStateOf(false) }

    fun closeSearch() {
        searchActive = false
        searchQuery = ""
    }

    BackHandler(enabled = searchActive) { closeSearch() }
    var selectedCategory by rememberSaveable { mutableStateOf<PromptCategory?>(null) }
    var favorites by remember { mutableStateOf(loadFavorites(context)) }
    var detailPrompt by remember { mutableStateOf<PromptItem?>(null) }
    var showCategoryPicker by remember { mutableStateOf(false) }

    fun toggleFavorite(id: String) {
        favorites = if (id in favorites) favorites - id else favorites + id
        saveFavorites(context, favorites)
    }

    fun copyPrompt(prompt: PromptItem) {
        clipboard.setText(AnnotatedString(prompt.prompt))
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = "Copied — paste it into ChatGPT or Gemini",
                duration = SnackbarDuration.Short
            )
        }
    }

    val discoverPrompts = PromptRepository.all.filter { item ->
        val matchesCategory = searchActive || selectedCategory == null || item.category == selectedCategory
        val matchesSearch = searchQuery.isBlank() ||
            item.title.contains(searchQuery, ignoreCase = true) ||
            item.prompt.contains(searchQuery, ignoreCase = true) ||
            item.tags.any { it.contains(searchQuery, ignoreCase = true) }
        matchesCategory && matchesSearch
    }
    val favoritePrompts = PromptRepository.all.filter { it.id in favorites }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            "Foto Prompts",
                            fontWeight = FontWeight.Black,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    ),
                    actions = {
                        if (selectedTab == 0) {
                            IconButton(onClick = {
                                if (searchActive) closeSearch() else searchActive = true
                            }) {
                                Icon(Icons.Filled.Search, contentDescription = "Search")
                            }
                        }
                    }
                )
                if (selectedTab == 0) {
                    AnimatedVisibility(visible = searchActive) {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .focusRequester(searchFocusRequester),
                            placeholder = { Text("Search styles, e.g. \"drone\" or \"anime\"") },
                            singleLine = true,
                            shape = RoundedCornerShape(16.dp),
                            trailingIcon = {
                                if (searchQuery.isNotEmpty()) {
                                    IconButton(onClick = { searchQuery = "" }) {
                                        Icon(Icons.Filled.Clear, contentDescription = "Clear search")
                                    }
                                }
                            }
                        )
                    }
                    LaunchedEffect(searchActive) {
                        if (searchActive) {
                            searchFocusRequester.requestFocus()
                            keyboardController?.show()
                        }
                    }
                    if (!searchActive) {
                        Surface(color = MaterialTheme.colorScheme.surfaceContainer) {
                            CategoryTray(
                                selectedCategory = selectedCategory,
                                onSelect = { selectedCategory = it },
                                onSeeAll = { showCategoryPicker = true }
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainer) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Filled.AutoAwesome, contentDescription = "Discover") },
                    label = { Text("Discover", fontWeight = FontWeight.Bold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {
                        BadgedBox(badge = {
                            if (favorites.isNotEmpty()) {
                                Badge(containerColor = FAVORITE_ACCENT) { Text("${favorites.size}") }
                            }
                        }) {
                            Icon(
                                imageVector = if (selectedTab == 1) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = "Favorites"
                            )
                        }
                    },
                    label = { Text("Favorites", fontWeight = FontWeight.Bold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = FAVORITE_ACCENT,
                        indicatorColor = FAVORITE_ACCENT,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }
    ) { padding ->
        val listToShow = if (selectedTab == 0) discoverPrompts else favoritePrompts
        if (listToShow.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (selectedTab == 0) {
                        "No prompts match your filters"
                    } else {
                        "No favorites yet — tap the heart on any style"
                    },
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(listToShow, key = { it.id }) { item ->
                    PromptPosterCard(
                        item = item,
                        isFavorite = item.id in favorites,
                        onToggleFavorite = { toggleFavorite(item.id) },
                        onCopy = { copyPrompt(item) },
                        onShare = { sharePrompt(context, item) },
                        onOpenDetail = { detailPrompt = item }
                    )
                }
            }
        }
    }

    detailPrompt?.let { item ->
        PromptDetailDialog(
            item = item,
            isFavorite = item.id in favorites,
            onDismiss = { detailPrompt = null },
            onToggleFavorite = { toggleFavorite(item.id) },
            onCopy = { copyPrompt(item) },
            onShare = { sharePrompt(context, item) }
        )
    }

    if (showCategoryPicker) {
        CategoryPickerSheet(
            selected = selectedCategory,
            onSelect = { selectedCategory = it },
            onDismiss = { showCategoryPicker = false }
        )
    }
}

@Composable
private fun CategoryTray(
    selectedCategory: PromptCategory?,
    onSelect: (PromptCategory?) -> Unit,
    onSeeAll: () -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            CategoryAvatar(
                emoji = "✨",
                label = "All",
                fullLabel = "All Styles",
                gradient = posterGradient(MaterialTheme.colorScheme.primary),
                isSelected = selectedCategory == null,
                onClick = { onSelect(null) }
            )
        }
        items(PromptCategory.entries.toList()) { category ->
            CategoryAvatar(
                emoji = category.emoji,
                label = category.shortLabel(),
                fullLabel = category.label,
                gradient = posterGradient(category.accent()),
                isSelected = selectedCategory == category,
                onClick = { onSelect(if (selectedCategory == category) null else category) }
            )
        }
        item {
            SeeAllAvatar(onClick = onSeeAll)
        }
    }
}

@Composable
private fun CategoryAvatar(
    emoji: String,
    label: String,
    fullLabel: String,
    gradient: Brush,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(68.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(gradient)
                .then(
                    if (isSelected) Modifier.border(3.dp, Color.White, CircleShape) else Modifier
                )
                .clickable(onClick = onClick)
                .semantics {
                    contentDescription = if (isSelected) "$fullLabel, selected" else fullLabel
                },
            contentAlignment = Alignment.Center
        ) {
            Text(emoji, fontSize = 24.sp)
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}

@Composable
private fun SeeAllAvatar(onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(68.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .border(1.dp, MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f), CircleShape)
                .clickable(onClick = onClick)
                .semantics { contentDescription = "See all styles" },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.GridView,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            text = "See all",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryPickerSheet(
    selected: PromptCategory?,
    onSelect: (PromptCategory?) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Text(
            "Choose a style",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                CategoryTile(
                    emoji = "✨",
                    label = "All Styles",
                    isSelected = selected == null,
                    accent = MaterialTheme.colorScheme.primary,
                    onClick = { onSelect(null); onDismiss() }
                )
            }
            items(PromptCategory.entries.toList()) { category ->
                CategoryTile(
                    emoji = category.emoji,
                    label = category.label,
                    isSelected = selected == category,
                    accent = category.accent(),
                    onClick = { onSelect(category); onDismiss() }
                )
            }
        }
    }
}

@Composable
private fun CategoryTile(
    emoji: String,
    label: String,
    isSelected: Boolean,
    accent: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.6f)
            .clip(RoundedCornerShape(20.dp))
            .background(posterGradient(accent))
            .clickable(onClick = onClick)
            .then(
                if (isSelected) {
                    Modifier.border(3.dp, Color.White, RoundedCornerShape(20.dp))
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.BottomStart
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(emoji, fontSize = 26.sp)
            Text(
                text = label,
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        if (isSelected) {
            Icon(
                Icons.Filled.CheckCircle,
                contentDescription = "Selected",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
                    .size(20.dp)
            )
        }
    }
}

@Composable
private fun TagPill(tag: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = Color.White.copy(alpha = 0.18f)
    ) {
        Text(
            text = "#$tag",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
private fun PosterIconButton(
    onClick: () -> Unit,
    containerColor: Color,
    iconTint: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String?
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(containerColor)
    ) {
        Icon(icon, contentDescription = contentDescription, tint = iconTint)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PromptPosterCard(
    item: PromptItem,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onCopy: () -> Unit,
    onShare: () -> Unit,
    onOpenDetail: () -> Unit
) {
    val accent = item.category.accent()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(posterGradient(accent))
            .clickable(onClick = onOpenDetail)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(shape = RoundedCornerShape(50), color = Color.White.copy(alpha = 0.18f)) {
                    Text(
                        "${item.category.emoji}  ${item.category.label}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
                PosterIconButton(
                    onClick = onToggleFavorite,
                    containerColor = Color.White.copy(alpha = 0.2f),
                    iconTint = if (isFavorite) FAVORITE_ACCENT else Color.White,
                    icon = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Toggle favorite"
                )
            }
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = item.prompt,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = Color.White.copy(alpha = 0.88f),
                modifier = Modifier.padding(top = 6.dp)
            )
            if (item.tags.isNotEmpty()) {
                FlowRow(
                    modifier = Modifier.padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    item.tags.forEach { tag -> TagPill(tag) }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PosterIconButton(
                    onClick = onShare,
                    containerColor = Color.White.copy(alpha = 0.2f),
                    iconTint = Color.White,
                    icon = Icons.Filled.Share,
                    contentDescription = "Share"
                )
                PosterIconButton(
                    onClick = onCopy,
                    containerColor = Color.White,
                    iconTint = iconTintOnWhite(accent),
                    icon = Icons.Filled.ContentCopy,
                    contentDescription = "Copy",
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PromptDetailDialog(
    item: PromptItem,
    isFavorite: Boolean,
    onDismiss: () -> Unit,
    onToggleFavorite: () -> Unit,
    onCopy: () -> Unit,
    onShare: () -> Unit
) {
    val accent = item.category.accent()
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                        .background(posterGradient(accent))
                        .padding(20.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(shape = RoundedCornerShape(50), color = Color.White.copy(alpha = 0.18f)) {
                                Text(
                                    "${item.category.emoji}  ${item.category.label}",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.labelMedium,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                )
                            }
                            PosterIconButton(
                                onClick = onDismiss,
                                containerColor = Color.White.copy(alpha = 0.2f),
                                iconTint = Color.White,
                                icon = Icons.Filled.Close,
                                contentDescription = "Close"
                            )
                        }
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            modifier = Modifier.padding(top = 14.dp)
                        )
                    }
                }
                Column(modifier = Modifier.padding(20.dp)) {
                    SelectionContainer {
                        Text(
                            text = item.prompt,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    if (item.tags.isNotEmpty()) {
                        FlowRow(
                            modifier = Modifier.padding(top = 14.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            item.tags.forEach { tag ->
                                Surface(shape = RoundedCornerShape(50), color = accent.copy(alpha = 0.18f)) {
                                    Text(
                                        text = "#$tag",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Medium,
                                        color = accent,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 18.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onToggleFavorite) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = "Toggle favorite",
                                tint = if (isFavorite) FAVORITE_ACCENT else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Button(
                            onClick = onShare,
                            modifier = Modifier.padding(start = 4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = accent.copy(alpha = 0.18f),
                                contentColor = accent
                            )
                        ) {
                            Icon(Icons.Filled.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                            Text("Share", modifier = Modifier.padding(start = 6.dp))
                        }
                        Button(
                            onClick = onCopy,
                            modifier = Modifier.padding(start = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = accent,
                                contentColor = contentColorFor(accent)
                            )
                        ) {
                            Icon(Icons.Filled.ContentCopy, contentDescription = null, modifier = Modifier.size(18.dp))
                            Text("Copy", modifier = Modifier.padding(start = 6.dp))
                        }
                    }
                }
            }
        }
    }
}

