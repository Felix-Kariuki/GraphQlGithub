package com.flexcode.githubgraphql

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.flexcode.graphql.presentation.components.FollowersFollowing
import com.flexcode.graphql.presentation.state.GithubState
import com.flexcode.graphql.presentation.views.GithubUserProfileScreen
import org.junit.Rule
import org.junit.Test

class UserProfileScreenTests {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun test_loader_is_displayed_when_load_state_loading() {
        rule.setContent {
            GithubUserProfileScreen(
                onFollowersClick = { },
                onFollowingClick = {  },
                onSeeAllClick = {  },
                state = GithubState(isLoading = true),
            )
        }
        rule.onNodeWithTag("profile_loader").assertIsDisplayed()
    }

    @Test
    fun test_followers_test_has_click_functions() {
        rule.setContent {
            FollowersFollowing(followers = "", following = "", onFollowersClick = { }) {}
        }
        rule.onNodeWithTag("followers_text").assertHasClickAction()
    }

    @Test
    fun test_following_test_has_click_functions() {
        rule.setContent {
            FollowersFollowing(followers = "", following = "", onFollowersClick = {  }) {}
        }
        rule.onNodeWithTag("following_text").assertHasClickAction()
    }
}
