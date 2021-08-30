package com.testdevlab.memorygame.injection

import com.testdevlab.memorygame.ui.GameViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [InjectionModule::class])
interface InjectionComponent {

    fun inject(target: GameViewModel)

}
