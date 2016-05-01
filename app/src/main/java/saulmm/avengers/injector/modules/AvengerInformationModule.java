/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package saulmm.avengers.injector.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import saulmm.avengers.CharacterDetailsUsecase;
import saulmm.avengers.GetCollectionUsecase;
import saulmm.avengers.entities.Character;
import saulmm.avengers.entities.CollectionItem;
import saulmm.avengers.injector.Activity;
import saulmm.avengers.CharacterDatasource;
import saulmm.avengers.repository.Repository;

@Module
public class AvengerInformationModule {
    private final int mCharacterId;

    public AvengerInformationModule(int characterId) {
        mCharacterId = characterId;
    }

    @Provides @Activity
    CharacterDetailsUsecase provideGetCharacterInformationUsecase (
        Repository<Character> repository,
        @Named("ui_thread") Scheduler uiThread,
        @Named("executor_thread") Scheduler executorThread) {

        return new CharacterDetailsUsecase(mCharacterId, repository, uiThread, executorThread);
    }

    @Provides @Activity
    GetCollectionUsecase provideGetCharacterComicsUsecase (
        Repository<CollectionItem> repository,
        @Named("ui_thread") Scheduler uiThread,
        @Named("executor_thread") Scheduler executorThread) {

        return new GetCollectionUsecase(mCharacterId, repository, uiThread, executorThread);
    }
}
