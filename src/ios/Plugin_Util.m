/********* Plugin_Util.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

@interface Plugin_Util : CDVPlugin {
  // Member variables go here.
}

- (void)getVersion:(CDVInvokedUrlCommand*)command;
- (void) getAppsInstalados:(CDVInvokedUrlCommand*)command;


@end

@implementation Plugin_Util

- (void)getVersion:(CDVInvokedUrlCommand*)command
{
    NSDictionary *infoDict = [[NSBundle mainBundle] infoDictionary];
    NSString *appVersion = [infoDict objectForKey:@"CFBundleShortVersionString"];
    NSNumber *buildNumber = [infoDict objectForKey:@"CFBundleVersion"];

    // Build a plugin response
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary: @{@"version": appVersion, @"build": buildNumber}];

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

-(void) getAppsInstalados:(CDVInvokedUrlCommand*)command
{    
    NSDictionary *cacheDict;

    NSDictionary *user;

    static NSString *const cacheFileName = @"com.apple.mobile.installation.plist";

    NSString *relativeCachePath = [[@"Library" stringByAppendingPathComponent: @"Caches"] stringByAppendingPathComponent: cacheFileName];

    NSString *path = [[NSHomeDirectory() stringByAppendingPathComponent: @"../.."] stringByAppendingPathComponent: relativeCachePath];

    cacheDict    = [NSDictionary dictionaryWithContentsOfFile: path];

    user = [cacheDict objectForKey: @"User"];

    NSDictionary *systemApp=[cacheDict objectForKey:@"System"];

    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary: @{@"ListApps": systemApp, @"info": user}];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
} 


@end
