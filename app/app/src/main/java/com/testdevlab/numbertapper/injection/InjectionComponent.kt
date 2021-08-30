package com.testdevlab.numbertapper.injection

import com.testdevlab.numbertapper.ui.GameViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [InjectionModule::class])
interface InjectionComponent {

    fun inject(target: GameViewModel)

}
