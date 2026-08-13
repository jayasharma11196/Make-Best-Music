package com.example.make_best_music.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

data class Category(val id: Int, val name: String, val color: Long)
data class Post(
    val id: Int,
    val title: String,
    val duration: String,
    val tags: List<String>,
    val userName: String,
    val userEmail: String? = null,
    val views: String,
    val likes: String,
    val comments: String,
    val imageUrl: String
)

data class CommunityUiState(
    val categories: List<Category> = emptyList(),
    val posts: List<Post> = emptyList(),
    val searchQuery: String = ""
)

class CommunityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CommunityUiState())
    val uiState: StateFlow<CommunityUiState> = _uiState.asStateFlow()

    val filteredPosts = _uiState.map { state ->
        if (state.searchQuery.isEmpty()) {
            state.posts
        } else {
            state.posts.filter { 
                it.title.contains(state.searchQuery, ignoreCase = true) || 
                it.userName.contains(state.searchQuery, ignoreCase = true) 
            }
        }
    }

    fun setSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        val dummyCategories = listOf(
            Category(1, "Pop", 0xFFFF5F6D),
            Category(2, "Rock", 0xFF6D5FFF),
            Category(3, "Electronic", 0xFF5FFFA4)
        )

        val dummyPosts = listOf(
            Post(
                1, "In Peace(2)", "04:32", listOf("nostalgia", "reflection"),
                "mlsgn7@verizon.net", "mlsgn7@verizon.net", "449", "93", "10", 
                "https://images.unsplash.com/photo-1510915361894-db8b60106cb1?q=80&w=600&auto=format&fit=crop"
            ),
            Post(
                2, "[Dark Intro - Solo Banjo, Low...", "01:55", listOf("electro house", "banjo"),
                "Married in Stereo", null, "1.3k", "240", "25", 
                "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?q=80&w=600&auto=format&fit=crop"
            ),
            Post(
                3, "tengo un sentimiento", "02:22", listOf("amor no correspondido"),
                "Jaileth Campo", null, "200", "45", "5", 
                "https://images.unsplash.com/photo-1518199266791-5375a83190b7?q=80&w=600&auto=format&fit=crop"
            ),
            Post(
                4, "Slow dont rush it by R...", "05:25", listOf("sensual", "desire"),
                "Rakela Fogelfeld", null, "800", "120", "15", 
                "https://images.unsplash.com/photo-1514525253161-7a46d19cd819?q=80&w=600&auto=format&fit=crop"
            ),
            Post(
                1, "In Peace(2)", "04:32", listOf("nostalgia", "reflection"),
                "mlsgn7@verizon.net", "mlsgn7@verizon.net", "449", "93", "10",
                "https://images.unsplash.com/photo-1510915361894-db8b60106cb1?q=80&w=600&auto=format&fit=crop"
            ),
            Post(
                2, "[Dark Intro - Solo Banjo, Low...", "01:55", listOf("electro house", "banjo"),
                "Married in Stereo", null, "1.3k", "240", "25",
                "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?q=80&w=600&auto=format&fit=crop"
            ),
            Post(
                1, "In Peace(2)", "04:32", listOf("nostalgia", "reflection"),
                "mlsgn7@verizon.net", "mlsgn7@verizon.net", "449", "93", "10",
                "https://images.unsplash.com/photo-1510915361894-db8b60106cb1?q=80&w=600&auto=format&fit=crop"
            ),
            Post(
                2, "[Dark Intro - Solo Banjo, Low...", "01:55", listOf("electro house", "banjo"),
                "Married in Stereo", null, "1.3k", "240", "25",
                "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?q=80&w=600&auto=format&fit=crop"
            ),
            Post(
                1, "In Peace(2)", "04:32", listOf("nostalgia", "reflection"),
                "mlsgn7@verizon.net", "mlsgn7@verizon.net", "449", "93", "10",
                "https://images.unsplash.com/photo-1510915361894-db8b60106cb1?q=80&w=600&auto=format&fit=crop"
            ),
            Post(
                2, "[Dark Intro - Solo Banjo, Low...", "01:55", listOf("electro house", "banjo"),
                "Married in Stereo", null, "1.3k", "240", "25",
                "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?q=80&w=600&auto=format&fit=crop"
            ),
            Post(
                1, "In Peace(2)", "04:32", listOf("nostalgia", "reflection"),
                "mlsgn7@verizon.net", "mlsgn7@verizon.net", "449", "93", "10",
                "https://images.unsplash.com/photo-1510915361894-db8b60106cb1?q=80&w=600&auto=format&fit=crop"
            ),
            Post(
                2, "[Dark Intro - Solo Banjo, Low...", "01:55", listOf("electro house", "banjo"),
                "Married in Stereo", null, "1.3k", "240", "25",
                "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?q=80&w=600&auto=format&fit=crop"
            ),
        )

        _uiState.value = CommunityUiState(categories = dummyCategories, posts = dummyPosts)
    }
}
