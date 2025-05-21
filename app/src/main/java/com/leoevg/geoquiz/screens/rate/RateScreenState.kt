package com.leoevg.geoquiz.screens.rate

data class RateScreenState(
    var stars: Int = 0,
    navigate: (NavigationPaths) -> Unit,
)