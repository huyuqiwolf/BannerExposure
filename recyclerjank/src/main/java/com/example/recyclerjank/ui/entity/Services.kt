package com.example.recyclerjank.ui.entity

import java.util.UUID

data class GridService(
    val uuid: UUID,
    val logoUrl: RemoteImage,
    val title: String,
    val desc: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GridService

        if (uuid != other.uuid) return false
        if (logoUrl != other.logoUrl) return false
        if (title != other.title) return false
        if (desc != other.desc) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + logoUrl.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + desc.hashCode()
        return result
    }
}

data class ListService(
    val uuid: UUID,
    val logoUrl: RemoteImage,
    val title: String,
    val desc: String,
    val content:String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ListService

        if (uuid != other.uuid) return false
        if (logoUrl != other.logoUrl) return false
        if (title != other.title) return false
        if (desc != other.desc) return false
        if (content != other.content) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + logoUrl.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + desc.hashCode()
        result = 31 * result + content.hashCode()
        return result
    }
}

data class BannerService(
    val uuid: UUID,
    val bannerUrl: RemoteImage
)

data class CardService(
    val uuid: UUID,
    val cardUrl: RemoteImage
)