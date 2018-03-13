package nl.topicus.overheid.javafactorybot

/**
 * Global manager for the factories which can be used to manage the factories. This manager is a singleton and can contain shared configuration
 * between factories.
 *
 * @author dennis
 * @since 04 Jan 2018
 */
@Singleton
class FactoryManager {
    /**
     * Context which should be used as the current context when {@link Factory#create} is used instead of {@link Factory#build}.
     */
    FactoryContext createContext = null

    /**
     * The context which should be used by the factories in the current execution.
     */
    FactoryContext currentContext = null
}