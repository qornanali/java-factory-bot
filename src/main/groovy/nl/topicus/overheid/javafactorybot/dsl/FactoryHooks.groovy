package nl.topicus.overheid.javafactorybot.dsl

import java.util.function.Consumer

trait FactoryHooks<M> {
    Consumer<Map<String, Object>> afterAttributesHook = { it }
    Consumer<M> afterBuildHook = { it }
    Consumer<M> afterCreateHook = { it }

    /**
     * Callback which is called after the values of the attributes are evaluated, but before the object itself is built.
     *
     * @param attributes The evaluated attributes
     * @return The attributes to use for creating the object
     */
    Map<String, Object> onAfterAttributes(Map<String, Object> attributes) {
        afterAttributesHook.accept(attributes)
        attributes
    }

    /**
     * Callback which is called after the model is built using the evaluated attributes, but before it is returned as
     * result of the build() method. This allows to tweak the model, for example to fix relationships.
     *
     * @param model The model after it was built using the evaluated attributes. Can be null
     * @return The model with possible alterations
     */
    M onAfterBuild(M model) {
        afterBuildHook.accept(model)
        model
    }

    /**
     * Callback which is called after the model is created (built and saved) using the evaluated attributes,
     * but before it is returned as result of the create() method. This allows to tweak the model,
     * for example to fix relationships.
     *
     * This callback is only used when models are created using {@link nl.topicus.overheid.javafactorybot.BaseFactory#create}
     *
     * @param model The model after it was created using the evaluated attributes. Can be null
     * @return The model with possible alterations
     */
    M onAfterCreate(M model) {
        afterCreateHook.accept(model)
        model
    }

    def after(@DelegatesTo(HooksBuilderDsl) Closure closure) {
        closure.delegate = new HooksBuilderDsl(this)
        closure()
    }
}