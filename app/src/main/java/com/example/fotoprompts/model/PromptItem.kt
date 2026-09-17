package com.example.fotoprompts.model

enum class PromptCategory(val label: String, val emoji: String) {
    COUPLES("Couples", "💑"),
    INDIAN_STYLES("Indian Styles", "🪔"),
    PHOTOGRAPHY("Photography", "📷"),
    ANIME_CARTOON("Cartoon & Anime", "🎬"),
    ART_PAINTING("Art & Painting", "🎨"),
    RETRO_VINTAGE("Retro & Vintage", "📼"),
    SCIFI_FUTURE("Sci-Fi & Future", "🚀"),
    FANTASY_HISTORY("Fantasy & History", "🐉"),
    FASHION_PRODUCT("Fashion & Product", "👗"),
    FUN_NOVELTY("Fun & Novelty", "🎉"),
    CAMERA_FX("Camera Effects", "🎥")
}

data class PromptItem(
    val id: String,
    val title: String,
    val category: PromptCategory,
    val prompt: String,
    val tags: List<String> = emptyList()
)
