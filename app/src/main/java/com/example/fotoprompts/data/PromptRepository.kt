package com.example.fotoprompts.data

import com.example.fotoprompts.model.PromptCategory
import com.example.fotoprompts.model.PromptItem
import com.example.fotoprompts.model.PromptCategory.ANIME_CARTOON
import com.example.fotoprompts.model.PromptCategory.ART_PAINTING
import com.example.fotoprompts.model.PromptCategory.CAMERA_FX
import com.example.fotoprompts.model.PromptCategory.COUPLES
import com.example.fotoprompts.model.PromptCategory.FANTASY_HISTORY
import com.example.fotoprompts.model.PromptCategory.FASHION_PRODUCT
import com.example.fotoprompts.model.PromptCategory.FUN_NOVELTY
import com.example.fotoprompts.model.PromptCategory.INDIAN_STYLES
import com.example.fotoprompts.model.PromptCategory.PHOTOGRAPHY
import com.example.fotoprompts.model.PromptCategory.RETRO_VINTAGE
import com.example.fotoprompts.model.PromptCategory.SCIFI_FUTURE
import com.example.fotoprompts.model.PromptCategory.TRENDING

/**
 * Curated, copy-paste-ready prompts for turning a photo into a stylized
 * image with ChatGPT / Gemini image generation. [TRENDING] entries are
 * ordered first since that's what most people are looking for.
 */
object PromptRepository {

    val all: List<PromptItem> = listOf(
        // ---- TRENDING ----
        PromptItem(
            "action_figure_box", "Action Figure Box", TRENDING,
            "Turn this photo into a realistic collectible action figure inside blister packaging: a plastic clamshell case, a printed cardboard backer that uses this photo as the character art, a few matching accessories laid out beside the figure, and a small nameplate with a fitting title.",
            listOf("figurine", "packaging", "viral")
        ),
        PromptItem(
            "pixar_3d", "3D Pixar Style", TRENDING,
            "Reimagine this photo as a 3D animated Pixar movie character: big expressive eyes, soft rounded features, warm cinematic lighting, and a colorful stylized background.",
            listOf("3d", "animated", "pixar")
        ),
        PromptItem(
            "ghibli_anime", "Studio Ghibli Anime", TRENDING,
            "Redraw this photo in the hand-painted Studio Ghibli anime art style: soft watercolor backgrounds, warm nostalgic lighting, gentle linework, and a dreamy, whimsical atmosphere like a Miyazaki film.",
            listOf("anime", "ghibli", "watercolor")
        ),
        PromptItem(
            "old_money", "Old Money Aesthetic", TRENDING,
            "Transform this photo into an old money aesthetic portrait: tailored neutral-tone clothing, soft natural light, a grand estate or library background, and a timeless, understated luxury feel.",
            listOf("fashion", "luxury", "portrait")
        ),
        PromptItem(
            "polaroid_memory", "90s Polaroid", TRENDING,
            "Turn this photo into a slightly faded 1990s Polaroid instant photo, with a white border, soft film grain, a warm color cast, and a handwritten date scrawled in the corner.",
            listOf("polaroid", "90s", "nostalgic")
        ),
        PromptItem(
            "figurine_shelf", "Collectible Figurine", TRENDING,
            "Turn this photo into a highly detailed collectible vinyl figurine displayed in its packaging box on a shelf, designer-toy style, with clean studio lighting and sharp product-photo detail.",
            listOf("figurine", "collectible", "toy")
        ),
        PromptItem(
            "red_carpet", "Red Carpet Celebrity", TRENDING,
            "Transform this photo into a red carpet paparazzi shot: flashing camera lights, a blurred press crowd in the background, a glamorous outfit, and a confident celebrity pose.",
            listOf("celebrity", "glamour", "event")
        ),
        PromptItem(
            "wanted_poster", "Wild West Wanted Poster", TRENDING,
            "Turn this photo into an Old West 'WANTED' poster: aged sepia paper texture, torn edges, bold western typography, and a reward amount printed at the bottom.",
            listOf("western", "poster", "sepia")
        ),
        PromptItem(
            "magazine_cover", "Magazine Cover", TRENDING,
            "Turn this photo into a glossy fashion magazine cover, complete with a masthead title, bold cover lines, professional retouching, and studio-quality lighting.",
            listOf("editorial", "cover", "glossy")
        ),
        PromptItem(
            "claymation_char", "Claymation Character", TRENDING,
            "Reimagine this photo as a claymation stop-motion character, with visible fingerprint textures, matte clay surfaces, and a handcrafted miniature set in the background.",
            listOf("claymation", "stopmotion", "handmade")
        ),
        PromptItem(
            "lego_minifig", "LEGO Minifigure", TRENDING,
            "Turn this photo into a LEGO minifigure version of the subject: the blocky plastic body, cylindrical head, printed facial expression, and a LEGO baseplate background.",
            listOf("lego", "toy", "blocky")
        ),
        PromptItem(
            "visual_novel", "Anime Visual Novel Portrait", TRENDING,
            "Redraw this photo as an anime dating-sim visual novel character portrait: large expressive eyes, cel-shaded coloring, and a soft bokeh background.",
            listOf("anime", "game", "portrait")
        ),
        PromptItem(
            "80s_style", "80s Style Portrait", TRENDING,
            "Transform this photo into an 80s glamour shot: big teased hair, bold shoulder pads, neon eyeshadow, a laser-grid studio background, and soft glam lighting like a retro mall photo studio portrait.",
            listOf("80s", "retro", "glam")
        ),
        PromptItem(
            "90s_style", "90s Style Photo", TRENDING,
            "Turn this photo into a 90s style photograph: soft film grain, warm nostalgic color tones, a candid snapshot feel, and a 90s fashion look like a photo pulled from an old yearbook.",
            listOf("90s", "retro", "nostalgic")
        ),

        // ---- COUPLES ----
        PromptItem(
            "couple_cinematic", "Cinematic Couple Portrait", COUPLES,
            "Turn this couple's photo into a cinematic movie-poster style portrait, with dramatic lighting, a romantic color grade, and a scenic backdrop.",
            listOf("cinematic", "romantic", "couple")
        ),
        PromptItem(
            "couple_fairytale", "Fairytale Couple", COUPLES,
            "Reimagine this couple as fairytale royalty, in elegant period costumes, standing in front of a castle at sunset.",
            listOf("fairytale", "royal", "romantic")
        ),
        PromptItem(
            "couple_90s_polaroid", "Retro 90s Couple Polaroid", COUPLES,
            "Turn this couple's photo into a nostalgic 90s polaroid snapshot, with warm film tones, a white border, and a candid, playful pose.",
            listOf("polaroid", "90s", "candid")
        ),
        PromptItem(
            "couple_bollywood", "Bollywood Couple Poster", COUPLES,
            "Turn this couple's photo into a vintage Bollywood movie poster romance scene, with bold hand-painted illustration style and a dramatic pose.",
            listOf("bollywood", "poster", "romantic")
        ),
        PromptItem(
            "couple_silhouette", "Silhouette Sunset Couple", COUPLES,
            "Reimagine this couple as a romantic silhouette against a vivid sunset sky, with warm orange and pink tones.",
            listOf("silhouette", "sunset", "romantic")
        ),
        PromptItem(
            "couple_anime", "Anime Couple Portrait", COUPLES,
            "Redraw this couple's photo in a soft anime art style, with gentle linework, warm lighting, and a dreamy backdrop.",
            listOf("anime", "couple", "dreamy")
        ),
        PromptItem(
            "couple_wedding_editorial", "Wedding Editorial Couple", COUPLES,
            "Turn this couple's photo into an elegant wedding editorial shoot, with soft romantic lighting and a luxurious outdoor setting.",
            listOf("wedding", "editorial", "romantic")
        ),
        PromptItem(
            "couple_comic", "Comic Book Couple", COUPLES,
            "Turn this couple's photo into a bold comic book panel, with ink outlines, halftone shading, and a dramatic caption bubble.",
            listOf("comic", "ink", "couple")
        ),
        PromptItem(
            "couple_hollywood", "Old Hollywood Couple", COUPLES,
            "Reimagine this couple in an old Hollywood glamour photograph: black-and-white, dramatic lighting, and 1950s styling.",
            listOf("hollywood", "glamour", "black-white")
        ),
        PromptItem(
            "couple_pixar", "Matching Cartoon Avatars", COUPLES,
            "Turn this couple's photo into matching Pixar-style 3D cartoon avatars, standing side by side with warm, expressive features.",
            listOf("3d", "pixar", "couple")
        ),

        // ---- INDIAN STYLES ----
        PromptItem(
            "indian_bollywood_poster", "Bollywood Movie Poster", INDIAN_STYLES,
            "Turn this photo into a vintage Bollywood movie poster: bold hand-painted illustration style, a dramatic pose, vivid colors, and a stylized retro movie title.",
            listOf("bollywood", "poster", "vintage")
        ),
        PromptItem(
            "indian_saree_portrait", "Traditional Saree Portrait", INDIAN_STYLES,
            "Reimagine this photo with the subject wearing an elegant traditional silk saree, intricate gold jewelry, and a warm, festive studio backdrop.",
            listOf("saree", "traditional", "portrait")
        ),
        PromptItem(
            "indian_rajasthani_royal", "Royal Rajasthani Portrait", INDIAN_STYLES,
            "Reimagine the subject of this photo as Rajasthani royalty: an ornate turban or maang tikka, richly embroidered royal attire, and a majestic palace backdrop.",
            listOf("royal", "rajasthani", "palace")
        ),
        PromptItem(
            "indian_mughal_miniature", "Mughal Miniature Painting", INDIAN_STYLES,
            "Repaint this photo in the intricate Mughal miniature painting style, with fine gold detailing, flat rich colors, and ornamental floral borders.",
            listOf("mughal", "miniature-art", "painting")
        ),
        PromptItem(
            "indian_diwali_glow", "Diwali Festival Glow", INDIAN_STYLES,
            "Reimagine this photo lit by the warm glow of diyas and fairy lights for Diwali, with a festive, joyful atmosphere and rich golden tones.",
            listOf("diwali", "festival", "golden-light")
        ),
        PromptItem(
            "indian_holi_colors", "Holi Color Splash", INDIAN_STYLES,
            "Turn this photo into a vibrant Holi festival portrait, with clouds of colorful powder (gulal) exploding in the background and joyful energy.",
            listOf("holi", "festival", "colorful")
        ),
        PromptItem(
            "indian_temple_backdrop", "South Indian Temple Backdrop", INDIAN_STYLES,
            "Reimagine this photo with an intricately carved South Indian temple gopuram in the background, warm golden-hour light, and traditional attire.",
            listOf("temple", "south-indian", "traditional")
        ),
        PromptItem(
            "indian_bharatanatyam", "Bharatanatyam Dance Pose", INDIAN_STYLES,
            "Reimagine the subject of this photo as a classical Bharatanatyam dancer, in a vivid traditional costume with detailed jewelry, mid-pose, with dramatic stage lighting.",
            listOf("dance", "classical", "costume")
        ),
        PromptItem(
            "indian_wedding_portrait", "Indian Wedding Portrait", INDIAN_STYLES,
            "Turn this photo into an elegant Indian wedding portrait: ornate bridal or groom attire, intricate detailing, and a decorated mandap background.",
            listOf("wedding", "bridal", "traditional")
        ),
        PromptItem(
            "indian_maharaja_royal", "Maharaja / Maharani Royal Portrait", INDIAN_STYLES,
            "Reimagine the subject of this photo as an Indian Maharaja or Maharani, adorned with a jeweled crown, royal silk robes, seated on an ornate throne.",
            listOf("royal", "maharaja", "throne")
        ),

        // ---- PHOTOGRAPHY ----
        PromptItem(
            "drone_view", "Drone Aerial View", PHOTOGRAPHY,
            "/droneview — Reimagine this scene as a top-down aerial drone photograph shot from high altitude, with dramatic scale, sharp detail, and natural daylight.",
            listOf("aerial", "drone", "wide-angle")
        ),
        PromptItem(
            "golden_hour", "Golden Hour Portrait", PHOTOGRAPHY,
            "Relight this photo as if it were shot during golden hour: warm low-angle sunlight, long soft shadows, and a glowing rim light around the subject.",
            listOf("warm-light", "portrait", "outdoor")
        ),
        PromptItem(
            "cinematic_still", "Cinematic Film Still", PHOTOGRAPHY,
            "Turn this photo into a cinematic movie still: widescreen letterbox bars, teal-and-orange color grading, shallow depth of field, and dramatic lighting.",
            listOf("cinematic", "movie", "color-grade")
        ),
        PromptItem(
            "film_noir", "Black & White Noir", PHOTOGRAPHY,
            "Convert this photo into a high-contrast black-and-white film noir photograph, with deep shadows, venetian-blind light patterns, and a moody atmosphere.",
            listOf("noir", "black-white", "moody")
        ),
        PromptItem(
            "studio_portrait", "Studio Portrait", PHOTOGRAPHY,
            "Reshoot this as a professional studio portrait: a seamless gray backdrop, three-point softbox lighting, and sharp commercial-quality detail.",
            listOf("studio", "portrait", "professional")
        ),
        PromptItem(
            "double_exposure", "Double Exposure", PHOTOGRAPHY,
            "Turn this photo into an artistic double-exposure image, blending the subject's silhouette with a forest or city-skyline texture.",
            listOf("double-exposure", "artistic", "blend")
        ),
        PromptItem(
            "long_exposure", "Long Exposure Light Trails", PHOTOGRAPHY,
            "Reimagine this photo at night with long-exposure light trails streaking through the background, while keeping the subject sharp and in focus.",
            listOf("night", "light-trails", "long-exposure")
        ),
        PromptItem(
            "macro_closeup", "Macro Close-Up", PHOTOGRAPHY,
            "Turn this into an extreme macro photograph, revealing fine texture and detail as if shot with a macro lens at very close range.",
            listOf("macro", "closeup", "detail")
        ),
        PromptItem(
            "fisheye_lens", "Fisheye Lens", PHOTOGRAPHY,
            "Reshoot this photo with an ultra-wide fisheye lens effect: exaggerated barrel distortion and a curved horizon.",
            listOf("fisheye", "wide-angle", "distortion")
        ),
        PromptItem(
            "nat_geo", "National Geographic Documentary", PHOTOGRAPHY,
            "Turn this photo into a National Geographic-style documentary photograph, with natural light, authentic candid framing, and rich earthy tones.",
            listOf("documentary", "candid", "nature")
        ),

        // ---- ANIME & CARTOON ----
        PromptItem(
            "shinkai_bg", "Makoto Shinkai Anime", ANIME_CARTOON,
            "Redraw this photo in Makoto Shinkai's anime style: hyper-detailed skies, glowing light, and cinematic anime background art.",
            listOf("anime", "background-art", "cinematic")
        ),
        PromptItem(
            "disney_2d", "Classic Disney 2D", ANIME_CARTOON,
            "Reimagine this photo as a classic hand-drawn 2D Disney animation character from the 1990s.",
            listOf("disney", "2d", "classic")
        ),
        PromptItem(
            "south_park", "South Park Style", ANIME_CARTOON,
            "Turn this photo into a South Park-style paper cutout cartoon character.",
            listOf("cartoon", "paper-cutout", "comedy")
        ),
        PromptItem(
            "simpsons_style", "Simpsons Style", ANIME_CARTOON,
            "Redraw this photo as a yellow-skinned Simpsons cartoon character in the show's signature flat art style.",
            listOf("cartoon", "flat-color", "sitcom")
        ),
        PromptItem(
            "chibi_anime", "Chibi Anime", ANIME_CARTOON,
            "Turn this photo into an adorable chibi anime character: an oversized head, tiny body, and big sparkly eyes.",
            listOf("chibi", "cute", "anime")
        ),
        PromptItem(
            "comic_panel", "Comic Book Panel", ANIME_CARTOON,
            "Turn this photo into a bold comic book panel with halftone dots, thick ink outlines, and dynamic action lines.",
            listOf("comic", "ink", "action")
        ),
        PromptItem(
            "manga_bw", "Manga Black & White", ANIME_CARTOON,
            "Redraw this photo as a black-and-white manga panel with screentone shading and expressive linework.",
            listOf("manga", "black-white", "screentone")
        ),
        PromptItem(
            "genshin_style", "Genshin Impact Game Style", ANIME_CARTOON,
            "Reimagine this photo as a character in the Genshin Impact video game art style, with vibrant cel-shaded rendering.",
            listOf("game-art", "cel-shaded", "anime")
        ),
        PromptItem(
            "dreamworks_3d", "DreamWorks 3D", ANIME_CARTOON,
            "Reimagine this photo as a 3D animated DreamWorks movie character, with expressive features and cinematic lighting.",
            listOf("3d", "animated", "dreamworks")
        ),
        PromptItem(
            "rick_morty", "Rick and Morty Style", ANIME_CARTOON,
            "Turn this photo into a Rick and Morty-style cartoon character, with the show's wobbly linework and simple flat colors.",
            listOf("cartoon", "sci-fi", "flat-color")
        ),

        // ---- ART & PAINTING ----
        PromptItem(
            "van_gogh", "Van Gogh Painting", ART_PAINTING,
            "Repaint this photo in the style of Van Gogh's 'Starry Night': thick swirling brushstrokes and vivid post-impressionist color.",
            listOf("painting", "post-impressionism", "brushstrokes")
        ),
        PromptItem(
            "renaissance_oil", "Renaissance Oil Portrait", ART_PAINTING,
            "Turn this photo into a Renaissance-era oil painting portrait, with classical lighting, a rich dark background, and museum-quality brushwork.",
            listOf("oil-painting", "classical", "portrait")
        ),
        PromptItem(
            "watercolor", "Watercolor Painting", ART_PAINTING,
            "Repaint this photo as a soft watercolor illustration, with loose brush edges and gentle color bleeds.",
            listOf("watercolor", "soft", "illustration")
        ),
        PromptItem(
            "pop_art", "Pop Art (Warhol)", ART_PAINTING,
            "Turn this photo into an Andy Warhol-style pop art piece, with bold flat colors and repeated silkscreen-style panels.",
            listOf("pop-art", "warhol", "bold-color")
        ),
        PromptItem(
            "cubism", "Cubism (Picasso)", ART_PAINTING,
            "Reimagine this photo in a Picasso-inspired cubist style, with fragmented geometric shapes and abstract facial features.",
            listOf("cubism", "picasso", "abstract")
        ),
        PromptItem(
            "impressionism", "Impressionism (Monet)", ART_PAINTING,
            "Repaint this photo in Claude Monet's impressionist style, with soft dappled brushstrokes and natural outdoor light.",
            listOf("impressionism", "monet", "outdoor")
        ),
        PromptItem(
            "surrealism", "Surrealism (Dali)", ART_PAINTING,
            "Turn this photo into a Salvador Dali-style surrealist painting, with dreamlike melting shapes and an impossible landscape.",
            listOf("surrealism", "dali", "dreamlike")
        ),
        PromptItem(
            "pencil_sketch", "Pencil Sketch", ART_PAINTING,
            "Turn this photo into a detailed graphite pencil sketch, with realistic shading and visible paper texture.",
            listOf("sketch", "graphite", "linework")
        ),
        PromptItem(
            "stained_glass", "Stained Glass Window", ART_PAINTING,
            "Reimagine this photo as a stained glass window, with bold black leading lines and vivid backlit colors.",
            listOf("stained-glass", "colorful", "art")
        ),
        PromptItem(
            "charcoal_portrait", "Charcoal Portrait", ART_PAINTING,
            "Turn this photo into a dramatic charcoal portrait drawing, with soft smudged shading and high-contrast highlights.",
            listOf("charcoal", "drawing", "dramatic")
        ),

        // ---- RETRO & VINTAGE ----
        PromptItem(
            "vhs_90s", "90s VHS", RETRO_VINTAGE,
            "Turn this photo into a low-fidelity 90s VHS camcorder still, with scan lines, a timestamp overlay, and washed-out color.",
            listOf("vhs", "90s", "retro")
        ),
        PromptItem(
            "y2k", "Y2K Aesthetic", RETRO_VINTAGE,
            "Reimagine this photo in a 2000s Y2K aesthetic: glossy chrome text, butterfly clips, and a cyber-pop color palette.",
            listOf("y2k", "2000s", "cyber-pop")
        ),
        PromptItem(
            "vaporwave", "Vaporwave", RETRO_VINTAGE,
            "Turn this photo into a vaporwave art piece, with pink-and-teal gradients, glitch textures, and Greek statue elements.",
            listOf("vaporwave", "aesthetic", "glitch")
        ),
        PromptItem(
            "synthwave", "Synthwave / Retrowave", RETRO_VINTAGE,
            "Reimagine this photo in a synthwave style: neon grid horizons, a purple-pink sunset, and glowing outlines.",
            listOf("synthwave", "neon", "retro")
        ),
        PromptItem(
            "70s_film", "70s Film Photo", RETRO_VINTAGE,
            "Turn this photo into a 1970s film photograph, with warm faded colors, soft grain, and vintage light leaks.",
            listOf("70s", "film-grain", "vintage")
        ),
        PromptItem(
            "travel_poster", "Vintage Travel Poster", RETRO_VINTAGE,
            "Turn this photo into a vintage 1950s travel poster, with a bold flat illustration style and retro typography.",
            listOf("poster", "vintage", "illustration")
        ),
        PromptItem(
            "sepia_old", "Sepia Old Photograph", RETRO_VINTAGE,
            "Convert this photo into an aged sepia-toned photograph from the early 1900s, with soft vignetting and a cracked texture.",
            listOf("sepia", "vintage", "old-photo")
        ),
        PromptItem(
            "kodachrome", "Kodachrome Slide", RETRO_VINTAGE,
            "Reimagine this photo as a vibrant 1960s Kodachrome slide photograph, with rich saturated color and warm tones.",
            listOf("kodachrome", "60s", "saturated")
        ),
        PromptItem(
            "propaganda_poster", "Retro Propaganda Poster", RETRO_VINTAGE,
            "Turn this photo into a retro wartime propaganda poster, with bold flat colors and a motivational slogan banner.",
            listOf("poster", "retro", "bold")
        ),
        PromptItem(
            "disposable_cam", "Disposable Camera", RETRO_VINTAGE,
            "Turn this photo into a candid 90s disposable camera snapshot, with flash glare, film grain, and a timestamp in the corner.",
            listOf("disposable-camera", "candid", "flash")
        ),

        // ---- SCI-FI & FUTURISTIC ----
        PromptItem(
            "cyberpunk_neon", "Cyberpunk Neon City", SCIFI_FUTURE,
            "Reimagine this photo in a cyberpunk setting: glowing neon signs, rain-soaked streets, and a futuristic night-city skyline.",
            listOf("cyberpunk", "neon", "futuristic")
        ),
        PromptItem(
            "holographic", "Holographic Projection", SCIFI_FUTURE,
            "Turn this photo into a holographic projection, with translucent blue scan lines and a floating light effect.",
            listOf("hologram", "futuristic", "digital")
        ),
        PromptItem(
            "astronaut_space", "Astronaut in Space", SCIFI_FUTURE,
            "Reimagine the subject of this photo as an astronaut floating in space, with Earth glowing in the background.",
            listOf("space", "astronaut", "sci-fi")
        ),
        PromptItem(
            "solarpunk", "Solarpunk Future", SCIFI_FUTURE,
            "Reimagine this photo in a solarpunk future: lush greenery integrated with solar-powered architecture and bright, optimistic lighting.",
            listOf("solarpunk", "future", "eco")
        ),
        PromptItem(
            "dieselpunk", "Dieselpunk", SCIFI_FUTURE,
            "Reimagine this photo in a dieselpunk style: 1940s industrial machinery, brass pipework, and dramatic sepia-toned lighting.",
            listOf("dieselpunk", "industrial", "retro-future")
        ),
        PromptItem(
            "biopunk", "Biopunk", SCIFI_FUTURE,
            "Reimagine this photo in a biopunk style, with organic bioluminescent textures fused with futuristic technology.",
            listOf("biopunk", "organic", "futuristic")
        ),
        PromptItem(
            "android_robot", "Robot / Android Version", SCIFI_FUTURE,
            "Turn the subject of this photo into a sleek humanoid android, with metallic plating and glowing circuit details.",
            listOf("robot", "android", "sci-fi")
        ),
        PromptItem(
            "glitch_art", "Glitch Art", SCIFI_FUTURE,
            "Turn this photo into a digital glitch art piece, with RGB channel shifts, pixel sorting, and datamosh distortion.",
            listOf("glitch", "digital", "distortion")
        ),
        PromptItem(
            "matrix_rain", "Matrix Digital Rain", SCIFI_FUTURE,
            "Reimagine this photo falling through a Matrix-style green digital code rain effect.",
            listOf("matrix", "digital", "code")
        ),
        PromptItem(
            "retro_future_50s", "50s Retro-Futurism", SCIFI_FUTURE,
            "Reimagine this photo as a 1950s vision of 'the future': atomic-age design, chrome fins, and optimistic pastel colors.",
            listOf("retro-future", "50s", "atomic-age")
        ),

        // ---- FANTASY & HISTORY ----
        PromptItem(
            "medieval_knight", "Medieval Knight", FANTASY_HISTORY,
            "Reimagine the subject of this photo as a medieval knight in polished plate armor, standing in front of a stone castle.",
            listOf("medieval", "knight", "armor")
        ),
        PromptItem(
            "viking_warrior", "Viking Warrior", FANTASY_HISTORY,
            "Reimagine the subject of this photo as a fierce Viking warrior, with a fur cloak and braided hair, and a longship in the background.",
            listOf("viking", "warrior", "history")
        ),
        PromptItem(
            "samurai_warrior", "Samurai Warrior", FANTASY_HISTORY,
            "Reimagine the subject of this photo as a samurai warrior in traditional armor, holding a katana beneath cherry blossoms.",
            listOf("samurai", "japan", "warrior")
        ),
        PromptItem(
            "fairy_tale", "Fairy Tale Illustration", FANTASY_HISTORY,
            "Turn this photo into a whimsical storybook fairy-tale illustration, with soft pastel colors and magical lighting.",
            listOf("fairytale", "storybook", "whimsical")
        ),
        PromptItem(
            "wild_west", "Wild West Outlaw", FANTASY_HISTORY,
            "Reimagine the subject of this photo as a Wild West outlaw wearing a duster coat and hat, in a dusty frontier town.",
            listOf("western", "outlaw", "frontier")
        ),
        PromptItem(
            "tarot_card", "Tarot Card", FANTASY_HISTORY,
            "Turn this photo into an ornate tarot card illustration, with mystical symbols, a gold filigree border, and a card title at the bottom.",
            listOf("tarot", "mystical", "illustration")
        ),
        PromptItem(
            "baroque_painting", "Baroque Painting", FANTASY_HISTORY,
            "Repaint this photo in the dramatic Baroque style, with deep chiaroscuro lighting and opulent period clothing.",
            listOf("baroque", "painting", "dramatic")
        ),
        PromptItem(
            "egyptian_pharaoh", "Egyptian Pharaoh", FANTASY_HISTORY,
            "Reimagine the subject of this photo as an ancient Egyptian pharaoh, adorned with gold jewelry and a headdress, in front of pyramids.",
            listOf("egyptian", "pharaoh", "history")
        ),
        PromptItem(
            "greek_god", "Greek Mythology God", FANTASY_HISTORY,
            "Reimagine the subject of this photo as a Greek mythological god or goddess, in flowing draped robes atop Mount Olympus.",
            listOf("greek", "mythology", "god")
        ),
        PromptItem(
            "steampunk_inventor", "Steampunk Inventor", FANTASY_HISTORY,
            "Reimagine the subject of this photo as a Victorian steampunk inventor, with brass goggles, gears, and a workshop background.",
            listOf("steampunk", "victorian", "gears")
        ),

        // ---- FASHION & PRODUCT ----
        PromptItem(
            "editorial_shoot", "Editorial Fashion Shoot", FASHION_PRODUCT,
            "Turn this photo into a high-fashion editorial magazine shoot, with dramatic studio lighting and a bold minimalist backdrop.",
            listOf("editorial", "fashion", "studio")
        ),
        PromptItem(
            "ecommerce_shot", "E-Commerce Product Shot", FASHION_PRODUCT,
            "Reshoot this as a clean e-commerce product photo on a pure white background, with soft, even studio lighting.",
            listOf("product", "ecommerce", "clean")
        ),
        PromptItem(
            "vogue_cover", "Vogue Cover", FASHION_PRODUCT,
            "Turn this photo into a Vogue magazine cover, with elegant typography, a bold masthead, and glossy editorial lighting.",
            listOf("magazine", "vogue", "editorial")
        ),
        PromptItem(
            "streetwear_lookbook", "Streetwear Lookbook", FASHION_PRODUCT,
            "Reimagine this photo as a streetwear brand lookbook shot, with an urban background and a confident candid pose.",
            listOf("streetwear", "lookbook", "urban")
        ),
        PromptItem(
            "perfume_ad", "Perfume Ad", FASHION_PRODUCT,
            "Turn this photo into a luxury perfume advertisement, with a dreamy soft-focus background and elegant product lighting.",
            listOf("perfume", "advertisement", "luxury")
        ),
        PromptItem(
            "doll_box", "Collectible Doll Box", FASHION_PRODUCT,
            "Turn this photo into a collectible fashion doll in retail packaging, complete with a colorful box, accessories, and branding text.",
            listOf("doll", "packaging", "toy")
        ),
        PromptItem(
            "runway_show", "Runway Fashion Show", FASHION_PRODUCT,
            "Reimagine this photo as a high-fashion runway show moment, with dramatic spotlighting and a blurred audience in the background.",
            listOf("runway", "fashion", "spotlight")
        ),
        PromptItem(
            "jewelry_macro", "Jewelry Macro Ad", FASHION_PRODUCT,
            "Turn this photo into a macro jewelry advertisement shot, with sparkling light reflections and a dark, luxurious background.",
            listOf("jewelry", "macro", "luxury")
        ),
        PromptItem(
            "sneaker_ad", "Sneaker Ad", FASHION_PRODUCT,
            "Reimagine this photo as a dynamic sneaker advertisement, with dramatic angled lighting and a smoke or motion-blur background.",
            listOf("sneaker", "advertisement", "dynamic")
        ),
        PromptItem(
            "hollywood_glam", "Old Hollywood Glamour", FASHION_PRODUCT,
            "Turn this photo into an old Hollywood glamour portrait, with dramatic black-and-white lighting and 1940s styling.",
            listOf("hollywood", "glamour", "black-white")
        ),

        // ---- FUN & NOVELTY ----
        PromptItem(
            "trading_card", "Trading Card", FUN_NOVELTY,
            "Turn this photo into a collectible trading card, with stats, a rarity border, and holographic foil styling.",
            listOf("trading-card", "collectible", "holo")
        ),
        PromptItem(
            "wax_figure", "Wax Museum Figure", FUN_NOVELTY,
            "Reimagine the subject of this photo as a hyper-realistic wax museum figure, displayed on a small podium with a plaque.",
            listOf("wax-figure", "museum", "realistic")
        ),
        PromptItem(
            "bobblehead", "Bobblehead", FUN_NOVELTY,
            "Turn this photo into a bobblehead figurine with an oversized head, standing on a small display base.",
            listOf("bobblehead", "figurine", "novelty")
        ),
        PromptItem(
            "balloon_animal", "Balloon Animal Sculpture", FUN_NOVELTY,
            "Reimagine the subject of this photo as if it were sculpted entirely out of twisted balloon animals.",
            listOf("balloon-art", "novelty", "sculpture")
        ),
        PromptItem(
            "ice_sculpture", "Ice Sculpture", FUN_NOVELTY,
            "Reimagine the subject of this photo as an elegant carved ice sculpture, glistening under event lighting.",
            listOf("ice-sculpture", "elegant", "event")
        ),
        PromptItem(
            "sand_sculpture", "Sand Sculpture", FUN_NOVELTY,
            "Reimagine the subject of this photo as a detailed sand sculpture on a beach.",
            listOf("sand-art", "beach", "sculpture")
        ),
        PromptItem(
            "origami_art", "Origami Paper Art", FUN_NOVELTY,
            "Turn this photo into an origami paper-folded sculpture, with crisp geometric folds and a single-color paper texture.",
            listOf("origami", "paper-art", "geometric")
        ),
        PromptItem(
            "lego_diorama", "LEGO Diorama Scene", FUN_NOVELTY,
            "Reimagine this entire photo as a miniature LEGO brick diorama scene, with blocky buildings and minifigures.",
            listOf("lego", "diorama", "miniature")
        ),
        PromptItem(
            "cardboard_cutout", "Cardboard Cutout Standee", FUN_NOVELTY,
            "Turn this photo into a life-size cardboard cutout standee, with a visible cardboard support stand at the back.",
            listOf("cardboard", "standee", "novelty")
        ),
        PromptItem(
            "snow_globe", "Snow Globe", FUN_NOVELTY,
            "Reimagine the subject of this photo as a miniature scene trapped inside a glass snow globe, with falling snow.",
            listOf("snow-globe", "miniature", "whimsical")
        ),

        // ---- CAMERA EFFECTS ----
        PromptItem(
            "tilt_shift", "Tilt-Shift Miniature", CAMERA_FX,
            "Apply a tilt-shift effect to this photo so the scene looks like a tiny miniature model, with shallow blur above and below the focal plane.",
            listOf("tilt-shift", "miniature", "blur")
        ),
        PromptItem(
            "infrared", "Infrared Photography", CAMERA_FX,
            "Convert this photo into an infrared photograph, with surreal white foliage and a dark, dramatic sky.",
            listOf("infrared", "surreal", "camera-fx")
        ),
        PromptItem(
            "thermal_imaging", "Thermal Imaging", CAMERA_FX,
            "Turn this photo into a thermal / heat-vision camera image, with a rainbow heat-map color gradient.",
            listOf("thermal", "heat-map", "camera-fx")
        ),
        PromptItem(
            "xray_style", "X-Ray Style", CAMERA_FX,
            "Reimagine this photo as an X-ray scan, showing a ghostly skeletal silhouette against a dark background.",
            listOf("xray", "skeletal", "camera-fx")
        ),
        PromptItem(
            "cctv_camera", "Security / CCTV Camera", CAMERA_FX,
            "Turn this photo into a grainy black-and-white CCTV security camera still, with a timestamp overlay in the corner.",
            listOf("cctv", "security-cam", "grainy")
        ),
        PromptItem(
            "bokeh_portrait", "Bokeh Light Portrait", CAMERA_FX,
            "Reshoot this photo with a shallow depth of field and dreamy bokeh lights blurred in the background.",
            listOf("bokeh", "shallow-focus", "dreamy")
        ),
        PromptItem(
            "rain_window", "Rain-Streaked Window", CAMERA_FX,
            "Reimagine this photo as if shot through a rain-streaked window, with soft blur and reflected city lights.",
            listOf("rain", "window", "reflection")
        ),
        PromptItem(
            "underwater", "Underwater Photography", CAMERA_FX,
            "Reimagine this photo as an underwater shot, with light rays filtering through the water and soft blue tones.",
            listOf("underwater", "ocean", "light-rays")
        ),
        PromptItem(
            "northern_lights", "Northern Lights Backdrop", CAMERA_FX,
            "Reimagine this photo with a vivid aurora borealis lighting up the night sky in the background.",
            listOf("aurora", "night-sky", "northern-lights")
        ),
        PromptItem(
            "wes_anderson", "Symmetrical Wes Anderson", CAMERA_FX,
            "Reframe this photo in a perfectly symmetrical Wes Anderson film style, with pastel color grading and centered composition.",
            listOf("wes-anderson", "symmetrical", "pastel")
        )
    )
}
