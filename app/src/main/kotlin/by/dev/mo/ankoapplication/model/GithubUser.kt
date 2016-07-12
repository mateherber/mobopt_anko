package by.dev.mo.ankoapplication.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GithubUser(@JsonProperty("login") val login: String,
                      @JsonProperty("id") val id: Int,
                      @JsonProperty("avatar_url") val avatarUrl: String,
                      @JsonProperty("gravatar_id") val gravatarId: String,
                      @JsonProperty("url") val url: String,
                      @JsonProperty("html_url") val htmlUrl: String,
                      @JsonProperty("followers_url") val followersUrl: String,
                      @JsonProperty("following_url") val followingUrl: String,
                      @JsonProperty("gists_url") val gistsUrl: String,
                      @JsonProperty("starred_url") val starredUrl: String,
                      @JsonProperty("subscriptions_url") val subscriptionsUrl: String,
                      @JsonProperty("organizations_url") val organizationsUrl: String,
                      @JsonProperty("repos_url") val reposUrl: String,
                      @JsonProperty("events_url") val eventsUrl: String,
                      @JsonProperty("received_events_url") val receivedEventsUrl: String,
                      @JsonProperty("type") val type: String,
                      @JsonProperty("site_admin") val siteAdmin: Boolean) {
}