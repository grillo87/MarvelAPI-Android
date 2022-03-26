package com.josegrillo.data.mapper

import com.josegrillo.data.entity.ThumbnailDTO
import com.josegrillo.data.utils.makeSecurePath

class ThumbnailMapperImpl : ThumbnailMapper {
    override fun map(input: ThumbnailDTO?): String? {
        return if (input?.extension?.isNotEmpty() == true && input.path?.isNotEmpty() == true) {
            "${input.path.makeSecurePath()}.${input.extension}"
        } else {
            null
        }
    }
}