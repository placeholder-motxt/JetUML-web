package dev.webfx.platform.boot.gwt;

import com.google.gwt.core.client.EntryPoint;
import dev.webfx.platform.boot.ApplicationBooter;
import dev.webfx.platform.boot.spi.ApplicationBooterProvider;
import dev.webfx.platform.reflect.RArray;

import static dev.webfx.platform.service.gwtj2cl.ServiceRegistry.*;

public final class GwtEntryPoint implements ApplicationBooterProvider, EntryPoint {

    @Override
    public void onModuleLoad() {
        registerArrayConstructors();
        registerServiceProviders();
        ApplicationBooter.start(this, null);
    }

    public static void registerArrayConstructors() {

    }

    public static void registerServiceProviders() {
        register(dev.webfx.platform.boot.spi.ApplicationModuleBooter.class, dev.webfx.kit.launcher.WebFxKitLauncherModuleBooter::new, dev.webfx.platform.boot.spi.impl.ApplicationJobsInitializer::new, dev.webfx.platform.boot.spi.impl.ApplicationJobsStarter::new);
        register(javafx.application.Application.class, org.jetuml.JetUML::new);
    }
}