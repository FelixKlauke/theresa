package de.d3adspace.theresa;

import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.MembersInjector;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.Element;
import com.google.inject.spi.InjectionPoint;
import com.google.inject.spi.TypeConverterBinding;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractTheresa implements Theresa {

  protected abstract Injector getInjector();

  @Override
  public <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> typeLiteral) {
    return getInjector().getMembersInjector(typeLiteral);
  }

  @Override
  public <T> MembersInjector<T> getMembersInjector(Class<T> aClass) {
    return getInjector().getMembersInjector(aClass);
  }

  @Override
  public Map<Key<?>, Binding<?>> getBindings() {
    return getInjector().getBindings();
  }

  @Override
  public Map<Key<?>, Binding<?>> getAllBindings() {
    return getInjector().getAllBindings();
  }

  @Override
  public <T> Binding<T> getBinding(Key<T> key) {
    return getInjector().getBinding(key);
  }

  @Override
  public <T> Binding<T> getBinding(Class<T> aClass) {
    return getInjector().getBinding(aClass);
  }

  @Override
  public <T> Binding<T> getExistingBinding(Key<T> key) {
    return getInjector().getExistingBinding(key);
  }

  @Override
  public <T> List<Binding<T>> findBindingsByType(TypeLiteral<T> typeLiteral) {
    return getInjector().findBindingsByType(typeLiteral);
  }

  @Override
  public <T> Provider<T> getProvider(Key<T> key) {
    return getInjector().getProvider(key);
  }

  @Override
  public <T> Provider<T> getProvider(Class<T> aClass) {
    return getInjector().getProvider(aClass);
  }

  @Override
  public <T> T getInstance(Key<T> key) {
    return getInjector().getInstance(key);
  }

  @Override
  public <T> T getInstance(Class<T> aClass) {
    return getInjector().getInstance(aClass);
  }

  @Override
  public Injector getParent() {
    return getInjector().getParent();
  }

  @Override
  public Injector createChildInjector(Iterable<? extends Module> iterable) {
    return getInjector().createChildInjector(iterable);
  }

  @Override
  public Injector createChildInjector(Module... modules) {
    return getInjector().createChildInjector(modules);
  }

  @Override
  public Map<Class<? extends Annotation>, Scope> getScopeBindings() {
    return getInjector().getScopeBindings();
  }

  @Override
  public Set<TypeConverterBinding> getTypeConverterBindings() {
    return getInjector().getTypeConverterBindings();
  }

  @Override
  public List<Element> getElements() {
    return getInjector().getElements();
  }

  @Override
  public Map<TypeLiteral<?>, List<InjectionPoint>> getAllMembersInjectorInjectionPoints() {
    return getInjector().getAllMembersInjectorInjectionPoints();
  }
}
