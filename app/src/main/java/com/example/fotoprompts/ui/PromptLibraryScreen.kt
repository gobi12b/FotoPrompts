package com.example.fotoprompts.ui

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
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

private val FAVORITE_ACCENT = Color(0xFFE8607B)

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

private fun PromptCategory.accent(): Color = CategoryAccents[this] ?: Color(0xFF6650A4)

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

    var searchQuery by rememberSaveable { mutableStateOf("") }
    var searchActive by rememberSaveable { mutableStateOf(false) }
    var selectedCategory by rememberSaveable { mutableStateOf<PromptCategory?>(null) }
    var favoritesOnly by rememberSaveable { mutableStateOf(false) }
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

    val visiblePrompts = PromptRepository.all.filter { item ->
        val matchesCategory = searchActive || selectedCategory == null || item.category == selectedCategory
        val matchesFavorites = !favoritesOnly || item.id in favorites
        val matchesSearch = searchQuery.isBlank() ||
            item.title.contains(searchQuery, ignoreCase = true) ||
            item.prompt.contains(searchQuery, ignoreCase = true) ||
            item.tags.any { it.contains(searchQuery, ignoreCase = true) }
        matchesCategory && matchesFavorites && matchesSearch
    }

    val showFavoritesSection = favorites.isNotEmpty() && !favoritesOnly && !searchActive && selectedCategory == null
    val favoritePrompts = if (showFavoritesSection) {
        PromptRepository.all.filter { it.id in favorites }
    } else {
        emptyList()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                        IconButton(onClick = { searchActive = !searchActive }) {
                            Icon(Icons.Filled.Search, contentDescription = "Search")
                        }
                        IconToggleButton(checked = favoritesOnly, onCheckedChange = { favoritesOnly = it }) {
                            BadgedBox(badge = {
                                if (favorites.isNotEmpty()) {
                                    Badge(containerColor = FAVORITE_ACCENT) { Text("${favorites.size}") }
                                }
                            }) {
                                Icon(
                                    imageVector = if (favoritesOnly) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                    contentDescription = "Show favorites only",
                                    tint = if (favoritesOnly) FAVORITE_ACCENT else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                )
                AnimatedVisibility(visible = searchActive) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        placeholder = { Text("Search styles, e.g. \"drone\" or \"anime\"") },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp)
                    )
                }
                if (!searchActive) {
                    Surface(color = MaterialTheme.colorScheme.surfaceContainer) {
                        CategoryFilterBar(
                            selectedCategory = selectedCategory,
                            onOpenPicker = { showCategoryPicker = true }
                        )
                    }
                }
            }
        }
    ) { padding ->
        if (visiblePrompts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No prompts match your filters",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (showFavoritesSection) {
                    item(key = "favorites_header") {
                        SectionHeader("❤️  Your Favorites", FAVORITE_ACCENT)
                    }
                    items(favoritePrompts, key = { "fav_${it.id}" }) { item ->
                        PromptCard(
                            item = item,
                            isFavorite = true,
                            onToggleFavorite = { toggleFavorite(item.id) },
                            onCopy = { copyPrompt(item) },
                            onShare = { sharePrompt(context, item) },
                            onOpenDetail = { detailPrompt = item }
                        )
                    }
                    item(key = "all_styles_header") {
                        SectionHeader("✨  All Styles", MaterialTheme.colorScheme.primary)
                    }
                }
                items(visiblePrompts, key = { it.id }) { item ->
                    PromptCard(
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
private fun SectionHeader(text: String, accent: Color) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Black,
        color = accent,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun CategoryFilterBar(
    selectedCategory: PromptCategory?,
    onOpenPicker: () -> Unit
) {
    val accent = selectedCategory?.accent() ?: MaterialTheme.colorScheme.primary
    val label = selectedCategory?.let { "${it.emoji}  ${it.label}" } ?: "✨  All Styles"
    Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
        Surface(
            onClick = onOpenPicker,
            shape = RoundedCornerShape(50),
            color = accent.copy(alpha = 0.14f),
            border = BorderStroke(1.dp, accent.copy(alpha = 0.5f))
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(label, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = accent)
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = "Choose a style",
                    tint = accent,
                    modifier = Modifier.padding(start = 2.dp)
                )
            }
        }
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
    ModalBottomSheet(onDismissRequest = onDismiss, sheetState = sheetState) {
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
                CategoryGridCard(
                    emoji = "✨",
                    label = "All Styles",
                    isSelected = selected == null,
                    accent = MaterialTheme.colorScheme.primary,
                    onClick = { onSelect(null); onDismiss() }
                )
            }
            items(PromptCategory.entries.toList()) { category ->
                CategoryGridCard(
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
private fun CategoryGridCard(
    emoji: String,
    label: String,
    isSelected: Boolean,
    accent: Color,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) accent.copy(alpha = 0.16f) else MaterialTheme.colorScheme.surfaceContainerHigh,
        border = if (isSelected) BorderStroke(1.5.dp, accent) else null,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, fontSize = 20.sp)
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = if (isSelected) accent else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Composable
private fun CategoryBadge(category: PromptCategory, accent: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(accent.copy(alpha = 0.16f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(category.emoji, fontSize = 15.sp)
        }
        Text(
            text = category.label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            color = accent,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun TagPill(tag: String, accent: Color) {
    Surface(
        shape = RoundedCornerShape(50),
        color = accent.copy(alpha = 0.12f)
    ) {
        Text(
            text = "#$tag",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = accent,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PromptCard(
    item: PromptItem,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onCopy: () -> Unit,
    onShare: () -> Unit,
    onOpenDetail: () -> Unit
) {
    val accent = item.category.accent()
    Card(
        onClick = onOpenDetail,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryBadge(item.category, accent)
                IconButton(onClick = onToggleFavorite) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Toggle favorite",
                        tint = if (isFavorite) FAVORITE_ACCENT else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = item.prompt,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
            if (item.tags.isNotEmpty()) {
                FlowRow(
                    modifier = Modifier.padding(top = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    item.tags.forEach { tag -> TagPill(tag, accent) }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledTonalButton(onClick = onShare) {
                    Icon(Icons.Filled.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                    Text("Share", modifier = Modifier.padding(start = 6.dp))
                }
                Button(onClick = onCopy, modifier = Modifier.padding(start = 8.dp)) {
                    Icon(Icons.Filled.ContentCopy, contentDescription = null, modifier = Modifier.size(18.dp))
                    Text("Copy", modifier = Modifier.padding(start = 6.dp))
                }
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
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Filled.Close, contentDescription = "Close")
                    }
                }
                Box(modifier = Modifier.padding(top = 6.dp, bottom = 14.dp)) {
                    CategoryBadge(item.category, accent)
                }
                SelectionContainer {
                    Text(
                        text = item.prompt,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (item.tags.isNotEmpty()) {
                    FlowRow(
                        modifier = Modifier.padding(top = 14.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        item.tags.forEach { tag -> TagPill(tag, accent) }
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
                    FilledTonalButton(onClick = onShare, modifier = Modifier.padding(start = 4.dp)) {
                        Icon(Icons.Filled.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                        Text("Share", modifier = Modifier.padding(start = 6.dp))
                    }
                    Button(onClick = onCopy, modifier = Modifier.padding(start = 8.dp)) {
                        Icon(Icons.Filled.ContentCopy, contentDescription = null, modifier = Modifier.size(18.dp))
                        Text("Copy", modifier = Modifier.padding(start = 6.dp))
                    }
                }
            }
        }
    }
}
