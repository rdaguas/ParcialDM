package com.aguas_roberto.parcial.ui.core

import com.aguas_roberto.parcial.data.network.entities.ResultMessage
import com.aguas_roberto.parcial.ui.entities.ArtistasDataUI

class FunctionExtensions
fun ResultMessage.toArtistasNobelUI(): ArtistasDataUI =
    ArtistasDataUI(
        this.message,
        this.name,
        this.profile_image
    )


