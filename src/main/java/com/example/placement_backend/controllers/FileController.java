package com.example.placement_backend.controllers;

public class FileController {
    @Autowired
    private StorageService storageService;

    @RequestMapping(value="/{fileName}", produces = "image/*")
    public void download(@PathVariable("fileName") String fileName, HttpServletResponse resp) {
        System.out.println("Loading file: " + fileName);
        Resource resource = storageService.load(fileName);
        if(resource != null) {
            try(InputStream in = resource.getInputStream()) {
                ServletOutputStream out = resp.getOutputStream();
                FileCopyUtils.copy(in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
