describe("Design Search Replacement Tests: ", function() {
    var search_path_mainpar = "master|global|page/mainpar|parsys";
    var target_tree_mainpar = {
        "root":{
            "isRoot":true,
            "data":{},
            "children":[
                {
                    "isRoot":false,
                    "data":"page",
                    "children":[
                        {
                            "isRoot":false,
                            "data":"parsys",
                            "children":[]
                        },
                        {
                            "isRoot":false,
                            "data":"mainpar",
                            "children":[]
                        }
                    ]
                },
                {
                    "isRoot":false,
                    "data":"global",
                    "children":[
                        {
                            "isRoot":false,
                            "data":"parsys",
                            "children":[]
                        },
                        {
                            "isRoot":false,
                            "data":"mainpar",
                            "children":[]
                        }
                    ]
                },
                {
                    "isRoot":false,
                    "data":"master",
                    "children":[
                        {
                            "isRoot":false,
                            "data":"parsys",
                            "children":[]
                        },
                        {
                            "isRoot":false,
                            "data":"mainpar",
                            "children":[]
                        }
                    ]
                }
            ]
        }
    };

    describe("Tree Construction ", function(){
        it("generates the expected tree for the mainpar", function(){
            var tree_under_test = Harbor.Overrides.SearchTree.buildSearchTree(search_path_mainpar);

            expect(tree_under_test).toEqual(target_tree_mainpar);
        });
    });

    describe("Design Search", function(){
        /*
            This test uses a content structure object grabbed directly out of a debug session with the following "cells"
            in use. The expected output was also grabbed directly from the same debug session.  The content object had superfluous junk trimmed out
         */
        var content_structure = {
            "page":{
                "par":{
                    "video":{
                        "profiles":[
                            "hq",
                            "firefoxhq"
                        ]
                    }
                }
            },
            "master":{
                "mainpar":{
                    "components":[
                        "/libs/foundation/components/image",
                        "/libs/foundation/components/text",
                        "group:Harbor"
                    ],
                    "columnrow":{
                        "column":{
                            "column-par":{
                                "components":"group:General"
                            }
                        }
                    }
                }
            }
        }
        var mainpar_path = "master|global|page/mainpar|parsys";
        var mainpar_columnRow_column_path = "master|global|page/mainpar|parsys/columnrow|parbase/column/column-par|parsys";

        it("finds a design for the mainpar (" +  mainpar_path + ")", function(){
            var search_tree = Harbor.Overrides.SearchTree.buildSearchTree(mainpar_path);

            var style_under_test =  Harbor.Overrides.SearchTree.DFsearch(search_tree, {
                searchConfiguration:  Harbor.Overrides.SearchTree.defaultSearchConfig(content_structure)
            });
            style_under_test = style_under_test.pop();
            //I expect, specifically, to know what that thing is also:
            expect(style_under_test).toEqual([
                "/libs/foundation/components/image",
                "/libs/foundation/components/text",
                "group:Harbor"
            ]);
        });

        it("finds a design for a columnRow column in the mainpar: (" + mainpar_columnRow_column_path + ")", function(){
            var search_tree = Harbor.Overrides.SearchTree.buildSearchTree(mainpar_columnRow_column_path);

            var style_under_test =  Harbor.Overrides.SearchTree.DFsearch(search_tree, {
                searchConfiguration:  Harbor.Overrides.SearchTree.defaultSearchConfig(content_structure)
            });
            style_under_test = style_under_test.pop();
            expect(style_under_test).toEqual("group:General");
        });
    });

    describe("Tree Pruning", function(){
        var content_structure = {
            "page":{
                "par":{
                    "video":{
                        "profiles":[
                            "hq",
                            "firefoxhq"
                        ]
                    }
                }
            },
            "master":{
                "mainpar":{
                    "components":[
                        "/libs/foundation/components/image",
                        "/libs/foundation/components/text",
                        "group:Harbor"
                    ],
                    "columnrow":{
                        "column":{
                            "column-par":{
                                "components":"group:General"
                            }
                        }
                    }
                }
            }
        };
        var searchConfiguration =  Harbor.Overrides.SearchTree.defaultSearchConfig(content_structure);

        beforeEach(function(){
            //set up spies to check on the search configuration object's prune calls.
            spyOn(searchConfiguration, "pruneBranch");
        });

        it("should prune the 'global' node out of the mainpar search tree", function(){
            var search_tree = Harbor.Overrides.SearchTree.buildSearchTree(search_path_mainpar);

            //sample search tree for the above sample content_structure.
            var node = {
                "root":{
                    "isRoot":true,
                    "data":{},
                    "children":[{
                        "isRoot":false,
                        "data":"global",
                        "children":[
                            {
                                "isRoot":false,
                                "data":"parsys",
                                "children":[]
                            },
                            {
                                "isRoot":false,
                                "data":"mainpar",
                                "children":[]
                            }
                        ]
                    }]
                }
            };

            var style_under_test =  Harbor.Overrides.SearchTree.DFsearch(search_tree, {
                searchConfiguration:  searchConfiguration
            });

            /*
                Checks prune results, the root node should not have been pruned, but the
                root's child global should have been.
             */
            expect(searchConfiguration.pruneBranch).toHaveBeenCalled();
            expect(searchConfiguration.pruneBranch.calls.length).toEqual(2);

        });
    });

});