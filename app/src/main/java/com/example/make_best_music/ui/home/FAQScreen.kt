package com.example.make_best_music.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp

data class FAQItem(val question: String, val answer: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAQScreen(onBackClick: () -> Unit) {
    val faqs = listOf(
        FAQItem(
            "Can I use the music I create to make money on Youtube or other platforms?",
            "Yes. You retain the rights as described in our Terms of Service. For commercial use please ensure you follow platform rules and attribution requirements when applicable."
        ),
        FAQItem(
            "How can I obtain the commercial rights to a song?",
            "Commercial rights are granted according to the subscription tier you purchase. Contact our support for clearances and licensing details for specific use-cases."
        ),
        FAQItem(
            "How can I earn more credits?",
            "You can buy credit bundles from the Billing page or subscribe to a higher tier plan which includes monthly credits."
        ),
        FAQItem(
            "What happens if I don't use all my credits?",
            "Unused credits may expire depending on promotional terms. Check the subscription details in your account for precise rules."
        ),
        FAQItem(
            "How are credits for the Yearly subscription distributed?",
            "Credits for yearly subscriptions are allocated upfront and are available immediately after purchase. If you need additional credits during the subscription period, contact support and we will assist you."
        ),
        FAQItem(
            "Why did my credits suddenly disappear?",
            "If you notice unexpected credit usage, please review your usage history in the app. If something looks wrong, contact support (contact@makebestmusic.com) with your account details and we will investigate."
        ),
        FAQItem(
            "Does subscribing to the Professional plan really allow unlimited music generation?",
            "To prevent misuse, we initially allocate 10,000 credits for Professional plan subscriptions. If these credits are insufficient for your creative needs during the subscription period, please contact us (contact@makebestmusic.com) at any time, and we will gladly add more credits free of charge."
        )
    )

    var expandedIndex by remember { mutableStateOf(-1) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "FAQs",
                        color = Color.White,
                        fontSize = 18.ssp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF141421)
                )
            )
        },
        containerColor = Color(0xFF141421)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.sdp)
        ) {
            itemsIndexed(faqs) { index, faq ->
                FAQRow(
                    faq = faq,
                    isExpanded = expandedIndex == index,
                    onClick = {
                        expandedIndex = if (expandedIndex == index) -1 else index
                    }
                )
                HorizontalDivider(color = Color(0xFF2B2B40), thickness = 1.sdp)
            }
        }
    }
}

@Composable
fun FAQRow(faq: FAQItem, isExpanded: Boolean, onClick: () -> Unit) {
    val rotation by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f, label = "rotation")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 20.sdp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = faq.question,
                color = Color.White,
                fontSize = 15.ssp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.sdp)
                    .rotate(rotation)
            )
        }
        
        AnimatedVisibility(visible = isExpanded) {
            Text(
                text = faq.answer,
                color = Color.Gray,
                fontSize = 14.ssp,
                modifier = Modifier.padding(top = 16.sdp)
            )
        }
    }
}
