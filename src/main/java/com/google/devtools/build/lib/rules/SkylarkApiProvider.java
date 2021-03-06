// Copyright 2014 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.rules;

import com.google.devtools.build.lib.analysis.TransitiveInfoCollection;
import com.google.devtools.build.lib.util.Preconditions;

/**
 * An abstract class for adding a Skylark API for the native providers.
 * Derived classes should declare functions to be used from Skylark.
 */
public abstract class SkylarkApiProvider {
  private TransitiveInfoCollection info;

  protected TransitiveInfoCollection getInfo() {
    return info;
  }

  public final void init(TransitiveInfoCollection info) {
    if (this.info != null) {
      // todo(dslomov): nuke this weird initialization mechanism.

      // Allow multiple calls.
      // It is possible for the Skylark rule to get a SkylarkApiProvider such as `target.java`
      // from its dependency and pass it on. It does not make a whole lot of sense, but we
      // shouldn't crash.
      return;
    }
    this.info = Preconditions.checkNotNull(info);
  }
}
