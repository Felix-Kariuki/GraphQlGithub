/**
 *
 * Copyright (c) 2024 Felix Kariuki.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package com.flexcode.graphql.data.mappers


import com.flexcode.GetFollowersQuery
import com.flexcode.GetFollowingQuery
import com.flexcode.GetRepositoriesQuery
import com.flexcode.GetUserProfileQuery
import com.flexcode.githubgraphql.domain.models.GithubCount
import com.flexcode.githubgraphql.domain.models.GithubFollowers
import com.flexcode.githubgraphql.domain.models.GithubRepositories
import com.flexcode.githubgraphql.domain.models.GithubUser
import com.flexcode.githubgraphql.domain.models.SingleFollower
import com.flexcode.githubgraphql.domain.models.SingleRepository

internal fun GetUserProfileQuery.OnUser.toGithubUser(): GithubUser {
    return GithubUser(
        avatarUrl = avatarUrl.toString(),
        bio = bio,
        email = email,
        followers = followers.toFollowers(),
        following = following.toFollowing(),
        location = location,
        login = login,
        name = name,
        starredRepositories = starredRepositories.toStarred(),
        twitterUsername = twitterUsername,
        websiteUrl = websiteUrl.toString(),
        organizations = organizations.toOrganizations(),
        repositories = repositories.toRepos(),

    )
}

internal fun GetFollowingQuery.Following.toFollowing(): GithubFollowers {
    return GithubFollowers(
        nodes = nodes?.map { it?.toSingleFollowing() } ?: ArrayList(),
        totalCount = totalCount,
    )
}

internal fun GetFollowingQuery.Node.toSingleFollowing(): SingleFollower {
    return SingleFollower(
        avatarUrl = avatarUrl.toString(),
        bio = bio,
        login = login,
        name = name,
    )
}

internal fun GetFollowersQuery.Followers.toFollowers(): GithubFollowers {
    return GithubFollowers(
        nodes = nodes?.map { it?.toSingleFollower() } ?: ArrayList(),
        totalCount = totalCount,
    )
}

internal fun GetFollowersQuery.Node.toSingleFollower(): SingleFollower {
    return SingleFollower(
        avatarUrl = avatarUrl.toString(),
        bio = bio,
        login = login,
        name = name,
    )
}

internal fun GetRepositoriesQuery.Repositories.toRepositories(): GithubRepositories {
    return GithubRepositories(
        nodes = nodes?.map { it?.toSingleRepository() } ?: ArrayList(),
    )
}

internal fun GetRepositoriesQuery.Node.toSingleRepository(): SingleRepository {
    return SingleRepository(
        description = description,
        name = name,
        pushedAt = pushedAt.toString(),
        stargazers = stargazers.toStarred(),
    )
}

internal fun GetRepositoriesQuery.Stargazers.toStarred(): GithubCount {
    return GithubCount(
        totalCount = totalCount,
    )
}

internal fun GetUserProfileQuery.Followers.toFollowers(): GithubCount {
    return GithubCount(
        totalCount = totalCount,
    )
}

internal fun GetUserProfileQuery.Following.toFollowing(): GithubCount {
    return GithubCount(
        totalCount = totalCount,
    )
}

internal fun GetUserProfileQuery.Repositories.toRepos(): GithubCount {
    return GithubCount(
        totalCount = totalCount,
    )
}

internal fun GetUserProfileQuery.Organizations.toOrganizations(): GithubCount {
    return GithubCount(
        totalCount = totalCount,
    )
}

internal fun GetUserProfileQuery.StarredRepositories.toStarred(): GithubCount {
    return GithubCount(
        totalCount = totalCount,
    )
}
