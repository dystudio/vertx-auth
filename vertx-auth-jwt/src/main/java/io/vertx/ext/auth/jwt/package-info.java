/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

/**
 * == The JWT auth provider
 *
 * This component contains an out of the box a JWT implementation.
 *
 * JSON Web Token is a simple way to send information in the clear (usually in a URL) whose contents can be verified to
 * be trusted. JWT are well suited for scenarios as:
 *
 * * In a Single Sign-On scenario where you want a separate authentication server that can then send user information in
 *   a trusted way.
 * * Stateless API servers, very well suited for sinple page applications.
 * * etc...
 *
 * Before deciding on using JWT, it's important to note that JWT does not encrypt the payload, it only signs it. You
 * should not send any secret information using JWT, rather you should send information that is not secret but needs to
 * be verified. For instance, sending a signed user id to indicate the user that should be logged in would work great!
 * Sending a user's password would be very, very bad.
 *
 * Its main advantages are:
 *
 * * It allows you to verify token authenticity.
 * * It has a json body to contain any variable amount of data you want.
 * * It's completely stateless.
 *
 * To create an instance of the provider you use {@link io.vertx.ext.auth.jwt.JWTAuth}. You specify the configuration
 * in a JSON object.
 *
 * Here's an example of creating a JWT auth provider:
 *
 * [source,java]
 * ----
 * {@link examples.Examples#example6}
 * ----
 *
 * A typical flow of JWT usage is that in your application you have one end point that issues tokens, this end point
 * should be running in SSL mode, there after you verify the request user, say by its username and password you would
 * do:
 *
 * [source,java]
 * ----
 * {@link examples.Examples#example7}
 * ----
 *
 * === The JWT keystore file
 *
 * This auth provider requires a keystore in the classpath or in the filesystem with either a {@link javax.crypto.Mac}
 * or a {@link java.security.Signature} in order to sign and verify the generated tokens.
 *
 * The implementation will, by default, look for the following aliases, however not all are required to be present. As
 * a good practice `HS256` should be present:
 * ----
 * `HS256`:: HMAC using SHA-256 hash algorithm
 * `HS384`:: HMAC using SHA-384 hash algorithm
 * `HS512`:: HMAC using SHA-512 hash algorithm
 * `RS256`:: RSASSA using SHA-256 hash algorithm
 * `RS384`:: RSASSA using SHA-384 hash algorithm
 * `RS512`:: RSASSA using SHA-512 hash algorithm
 * `ES256`:: ECDSA using P-256 curve and SHA-256 hash algorithm
 * `ES384`:: ECDSA using P-384 curve and SHA-384 hash algorithm
 * `ES512`:: ECDSA using P-521 curve and SHA-512 hash algorithm
 * ----
 *
 * When no keystore is provided the implementation falls back in unsecure mode and signatures will not be verified, this
 * is useful for the cases where the payload if signed and or encrypted by external means.
 *
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@Document(fileName = "index.adoc")
@GenModule(name = "vertx-auth-jwt")
package io.vertx.ext.auth.jwt;

import io.vertx.codegen.annotations.GenModule;
import io.vertx.docgen.Document;