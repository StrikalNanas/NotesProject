package com.example.data.model

import com.example.domain.model.NoteDomain

fun NoteDomain.toData(): NoteData =
    NoteData(
        id = id,
        title = title,
        content = content
    )

fun NoteData.toDomain(): NoteDomain =
    NoteDomain(
        id = id,
        title = title,
        content = content
    )