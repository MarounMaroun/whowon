installation_path = /usr/local/bin
jar_dir = wcjar

install:
	@sudo mkdir $(installation_path)/$(jar_dir)
	@sudo cp app/worldcup-assembly-0.1.jar $(installation_path)/$(jar_dir)
	@sudo cp app/whowon $(installation_path)/
	@echo "Successfully installed whowon!"

remove:
	@sudo rm -rf $(installation_path)/$(jar_dir) $(installation_path)/whowon
	@echo "Removed!"
